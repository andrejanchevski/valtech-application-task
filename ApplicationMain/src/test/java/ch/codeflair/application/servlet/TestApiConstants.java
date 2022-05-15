package ch.codeflair.application.servlet;

import ch.codeflair.application.service.domain.Customer;

public class TestApiConstants {
    public static final Long CUSTOMER_ID_001 = 1L;

    public static final Long NON_EXISTENT_CUSTOMER = 10L;
    public static final String COMPANY_NAME = "Codeflair";
    public static final Customer customer =
            new Customer(1L, "Your", "Name", "1234 Yourplace", "Codeflair");
    public static final String EXPECTED_RESPONSE =
            "<div class='customerdata'>" +
                    "<h1>Sehr geehrter Your Name</h1>" +
                    "<div class='text'>Danke dass Sie sich bei " + COMPANY_NAME + " beworben haben!</div>" +
                    "</div>";
    public static final String EXPECTED_ERROR_RESPONSE =
            "<div class='customerdata'>" +
                    "<div class='text'>Ein Fehler ist aufgetreten</div>" +
                    "</div>";

}
