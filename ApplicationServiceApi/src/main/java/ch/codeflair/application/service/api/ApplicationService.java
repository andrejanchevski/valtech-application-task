package ch.codeflair.application.service.api;

/**
 * Created by benzahler on 07/05/15.
 */
public interface ApplicationService {

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
     * @throws ApplicationServiceException exception thrown if an unexpected problem occurs
     */
    String getCustomerData(String customerId) throws ApplicationServiceException;
}
