package ch.codeflair.application.servlet;

import ch.codeflair.application.mock.MockConstants;
import ch.codeflair.application.service.ImprovedApplicationServiceImpl;
import ch.codeflair.application.service.domain.Customer;
import ch.codeflair.application.service.exceptions.CustomerNotFoundException;
import ch.codeflair.application.service.impl.ApplicationServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import ch.codeflair.application.service.api.ApplicationServiceException;

import javax.servlet.ServletException;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * Created by benzahler on 07/05/15.
 */

@RunWith(MockitoJUnitRunner.class)
public class TestApplicationServlet {

    @Mock
    SlingHttpServletRequest request;
    @Mock
    ImprovedApplicationServiceImpl applicationService;

    @Test
    public void testDoGet() throws ServletException, IOException, NoSuchFieldException, IllegalAccessException, ApplicationServiceException {
        Mockito.when(request.getParameter(ApiConstants.REQUEST_PARAM_CUSTOMER_ID))
                .thenReturn(TestApiConstants.CUSTOMER_ID_001.toString());
        Mockito.when(request.getParameter(ApiConstants.REQUEST_PARAM_COMPANY))
                .thenReturn(TestApiConstants.COMPANY_NAME);
        ApplicationServlet servlet = new ApplicationServlet();
        Mockito.when(applicationService.
                findCustomerById(TestApiConstants.CUSTOMER_ID_001, TestApiConstants.COMPANY_NAME))
                .thenReturn(TestApiConstants.customer);
        setField(servlet, "applicationService", applicationService);
        MockSlingHttpServletResponse mockResponse = new MockSlingHttpServletResponse();
        servlet.doGet(request, mockResponse);
        Assert.assertTrue(StringUtils.equals(mockResponse.getOutputAsString(), TestApiConstants.EXPECTED_RESPONSE));
        Assert.assertTrue(mockResponse.getStatus() == 200);
    }

    @Test
    public void testDoGetCustomerError() throws ServletException, IOException, NoSuchFieldException, IllegalAccessException, ApplicationServiceException {
        Mockito.when(request.getParameter(ApiConstants.REQUEST_PARAM_CUSTOMER_ID))
                .thenReturn(TestApiConstants.NON_EXISTENT_CUSTOMER.toString());
        Mockito.when(request.getParameter(ApiConstants.REQUEST_PARAM_COMPANY))
                .thenReturn(TestApiConstants.COMPANY_NAME);
        ApplicationServlet servlet = new ApplicationServlet();
        Mockito.when(applicationService.
                        findCustomerById(TestApiConstants.NON_EXISTENT_CUSTOMER, TestApiConstants.COMPANY_NAME))
                .thenThrow(
                        new CustomerNotFoundException(String.format("Customer with id=%s does not exist",
                                TestApiConstants.NON_EXISTENT_CUSTOMER)));
        setField(servlet, "applicationService", applicationService);
        MockSlingHttpServletResponse mockResponse = new MockSlingHttpServletResponse();
        servlet.doGet(request, mockResponse);
        Assert.assertTrue(StringUtils.equals(mockResponse.getOutputAsString(), TestApiConstants.EXPECTED_ERROR_RESPONSE));
        Assert.assertTrue(mockResponse.getStatus() == 200);
    }


    /**
     * very limited helper to set field using reflections.<br>
     * this method does not have to be improved
     *
     * @param target the target object
     * @param name   the name of the field
     * @param value  the value to set
     */
    private static void setField(final Object target, final String name, final Object value) throws IllegalAccessException, NoSuchFieldException {
        final Class<?> resolverClass = target.getClass();
        final Field field = resolverClass.getDeclaredField(name);
        if (field != null) {
            field.setAccessible(true);
            field.set(target, value);
        }
    }


}
