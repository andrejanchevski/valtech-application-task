package ch.codeflair.application.service.domain;

import java.util.Objects;

public class Customer {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String company;

    public Customer(Long id, String firstName, String lastName, String address, String company) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.company = company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(firstName, customer.firstName) && Objects.equals(lastName, customer.lastName) && Objects.equals(address, customer.address) && Objects.equals(company, customer.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, address, company);
    }
}
