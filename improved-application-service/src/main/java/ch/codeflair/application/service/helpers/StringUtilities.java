package ch.codeflair.application.service.helpers;

import ch.codeflair.application.service.domain.Customer;

public class StringUtilities {

    public StringUtilities() {
    }

    public Customer createCustomerFromString(Long customerId, String customerString, String companyName) {
        String[] customerData = customerString.split("\\s+");
        return new Customer(customerId,
                customerData[0],
                customerData[1],
                customerData[3] + customerData[4],
                companyName);
    }


}
