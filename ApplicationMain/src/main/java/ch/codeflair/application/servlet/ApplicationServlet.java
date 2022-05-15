package ch.codeflair.application.servlet;

import ch.codeflair.application.service.ImprovedApplicationService;
import ch.codeflair.application.service.ImprovedApplicationServiceImpl;
import ch.codeflair.application.service.domain.Customer;
import org.apache.felix.scr.annotations.Reference;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

import javax.servlet.ServletException;
import java.io.IOException;
import java.nio.charset.Charset;


/**
 * The requirement of this Servlet is to deliver a defined html snippet with a salutation of the user.
 * The request contains a parameter "customerId" and a parameter "company".
 * The customerId can be used to retrieve customer data in the backend service.
 * Created by benzahler on 07/05/15.
 */
public class ApplicationServlet extends SlingSafeMethodsServlet {

    // if not familiar with CQ and Apache Felix: @Reference allows to inject service implementations at runtime
    // This is the same functionality as @Inject/@Autowired in JEE/Spring
    @Reference
    ImprovedApplicationService applicationService = new ImprovedApplicationServiceImpl();

    @Reference
    GenerateHTMLTemplates generateHTMLTemplates = new GenerateHTMLTemplates();


    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        try{
            Long customerId = Long.parseLong(request.getParameter(ApiConstants.REQUEST_PARAM_CUSTOMER_ID));
            String company = request.getParameter(ApiConstants.REQUEST_PARAM_COMPANY);
            Customer customer = applicationService.findCustomerById(customerId, company);
            response.getOutputStream().print(generateHTMLTemplates.generateCustomerHtmlTemplate(customer));
            response.setCharacterEncoding(Charset.defaultCharset().displayName());
        }
        catch (Exception e){
            response.getOutputStream().print(generateHTMLTemplates.generateCustomerErrorTemplate());
            response.setCharacterEncoding(Charset.defaultCharset().displayName());
        }
    }
}
