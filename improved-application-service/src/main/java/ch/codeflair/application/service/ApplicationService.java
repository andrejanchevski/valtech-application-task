package ch.codeflair.application.service;

import ch.codeflair.application.service.domain.Customer;

import java.util.List;

public interface ApplicationService {

    Customer findCustomerById(Long customerId, String companyName);
    List<Customer> findAllCustomers();

}
