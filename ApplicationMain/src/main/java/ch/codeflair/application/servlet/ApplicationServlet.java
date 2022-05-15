package ch.codeflair.application.servlet;

import ch.codeflair.application.service.impl.ApplicationServiceImpl;
import org.apache.felix.scr.annotations.Reference;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import ch.codeflair.application.service.api.ApplicationService;

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

    private static final String HTML_TEMPLATE =
            "<div class='customerdata'>" +
                    "<h1>Sehr geehrter ${firstname} ${lastname}</h1>" +
                    "<div class='text'>Danke dass Sie sich bei ${company} beworben haben!</div>" +
                    "</div>";
    private static final String ERROR_HTML =
            "<div class='customerdata'>" +
                    "<div class='text'>Ein Fehler ist aufgetreten</div>" +
                    "</div>";
    public static final String REQUEST_PARAM_CUSTOMER_ID = "customerId";
    public static final String REQUEST_PARAM_COMPANY = "company";

    // if not familiar with CQ and Apache Felix: @Reference allows to inject service implementations at runtime
    // This is the same functionality as @Inject/@Autowired in JEE/Spring
    @Reference
    ApplicationService applicationService = new ApplicationServiceImpl();

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        String customerId = request.getParameter(REQUEST_PARAM_CUSTOMER_ID);
        String company = request.getParameter(REQUEST_PARAM_COMPANY);
        String customerData = ((ApplicationServiceImpl) applicationService).getCustomerData(customerId);
        String customerLastName = customerData.substring(0, customerData.indexOf(" "));
        String customerFirstName = customerData.substring(20, customerData.indexOf(" ", 20));
        String htmlResponse = HTML_TEMPLATE.replace("${firstname}",
                customerFirstName).replace("${lastname}",
                customerLastName).replace("${company}", company);
        response.getOutputStream().print(htmlResponse);
        response.setCharacterEncoding(Charset.defaultCharset().displayName());
    }
}
