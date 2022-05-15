package ch.codeflair.application.service.impl;

import ch.codeflair.application.mock.MockConstants;
import org.apache.commons.lang3.StringUtils;
import ch.codeflair.application.service.api.ApplicationService;

/**
 * This is a Dummy implementation of a legacy backend service.
 * Do not modify this class.
 * <p/>
 * Created by benzahler on 07/05/15.
 */
public class ApplicationServiceImpl implements ApplicationService {


    /**
     * Returns customer data in a backend format: One string contains four fields, each field is of fixed length.
     * Name: 20 Characters
     * First name: 20 Characters
     * Zip Code: 5 Characters
     * Place: 15 Character
     * The total length of the String is always 60 Characters, space characters are used to fill fields
     *
     * @param customerId id of the customer to retrieve data for.
     * @return a string containing the customer data or null if the customer was not found
     */
    public String getCustomerData(String customerId) {
        if (StringUtils.equals(customerId, "001")) {
            return MockConstants.CUSTOMER_DATA_1;
        }
        if (StringUtils.equals(customerId, "002")) {
            return MockConstants.CUSTOMER_DATA_2;
        }
        if (StringUtils.equals(customerId, "003")) {
            return MockConstants.CUSTOMER_DATA_3;
        }
        return null;
    }
}
