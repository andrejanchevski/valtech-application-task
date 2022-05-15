package ch.codeflair.application.servlet;

import ch.codeflair.application.service.domain.Customer;

public class GenerateHTMLTemplates {

    public GenerateHTMLTemplates(){}

    public String generateCustomerHtmlTemplate(Customer customer){
        return String.format(
                "<div class='customerdata'>" +
                        "<h1>Sehr geehrter %s %s</h1>" +
                        "<div class='text'>Danke dass Sie sich bei %s beworben haben!</div>" +
                        "</div>", customer.getFirstName(), customer.getLastName(), customer.getCompany());
    }

    public String generateCustomerErrorTemplate(){
        return "<div class='customerdata'>" +
                "<div class='text'>Ein Fehler ist aufgetreten</div>" +
                "</div>";
    }



}
