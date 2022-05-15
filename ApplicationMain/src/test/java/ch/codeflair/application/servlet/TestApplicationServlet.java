package ch.codeflair.application.servlet;

import ch.codeflair.application.mock.MockConstants;
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


    private static final String CUSTOMER_ID_001 = "001";
    private static final String COMPANY_NAME = "Codeflair";

    private static final String EXPECTED_RESPONSE =
            "<div class='customerdata'>" +
                    "<h1>Sehr geehrter Your Name</h1>" +
                    "<div class='text'>Danke dass Sie sich bei " + COMPANY_NAME + " beworben haben!</div>" +
                    "</div>";

    @Mock
    SlingHttpServletRequest request;
    @Mock
    ApplicationServiceImpl applicationService;

    @Test
    public void testDoGet() throws ServletException, IOException, NoSuchFieldException, IllegalAccessException, ApplicationServiceException {
        Mockito.when(request.getParameter(ApplicationServlet.REQUEST_PARAM_CUSTOMER_ID)).thenReturn(CUSTOMER_ID_001);
        Mockito.when(request.getParameter(ApplicationServlet.REQUEST_PARAM_COMPANY)).thenReturn(COMPANY_NAME);
        ApplicationServlet servlet = new ApplicationServlet();
        Mockito.when(applicationService.getCustomerData(CUSTOMER_ID_001)).thenReturn(MockConstants.CUSTOMER_DATA_1);
        setField(servlet, "applicationService", applicationService);
        MockSlingHttpServletResponse mockResponse = new MockSlingHttpServletResponse();
        servlet.doGet(request, mockResponse);
        Assert.assertTrue(StringUtils.equals(mockResponse.getOutputAsString(), EXPECTED_RESPONSE));
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
