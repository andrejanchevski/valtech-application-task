package ch.codeflair.application.service;

import ch.codeflair.application.mock.repository.GenericRepository;
import ch.codeflair.application.mock.repository.GenericRepositoryImpl;
import ch.codeflair.application.service.domain.Customer;
import ch.codeflair.application.service.exceptions.CustomerNotFoundException;
import ch.codeflair.application.service.helpers.StringUtilities;
import org.apache.felix.scr.annotations.Reference;

import java.util.List;
import java.util.stream.Collectors;

public class ImprovedApplicationServiceImpl implements ImprovedApplicationService {


    @Reference
    StringUtilities stringUtilities = new StringUtilities();

    @Reference
    GenericRepository<String, Long> genericRepository = new GenericRepositoryImpl();

    @Override
    public Customer findCustomerById(Long customerId, String companyName) {
        return genericRepository.findById(customerId).map(it ->
                this.stringUtilities.createCustomerFromString(customerId,it, companyName)
        ).orElseThrow(
                () -> new CustomerNotFoundException(String.format("Customer with id=%s does not exist", customerId)));
    }

    @Override
    public List<Customer> findAllCustomers() {
        return genericRepository.findAll().stream()
                .map(it -> stringUtilities.createCustomerFromString(null, it, ""))
                .collect(Collectors.toList());
    }
}
