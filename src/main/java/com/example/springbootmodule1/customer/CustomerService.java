package com.example.springbootmodule1.customer;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service // this gives CustomerService to the call at CustomerController
public class CustomerService {

    private final CustomerRepository customerRepository;
    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long customerId) {
        if (customerRepository.findById(customerId).isEmpty()) {
            throw new IllegalStateException("Customer not found.");
        }
        return customerRepository.findById(customerId);
    }

    public void addNewCustomer(Customer customer) {
        System.out.println(customer);
        Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(customer.getEmail());
        if (customerOptional.isPresent()) {
            throw new IllegalStateException("Email already in use.");
        }
        customerRepository.save(customer);
    }

    public void deleteCustomer(Long customerId) {
        boolean exists = customerRepository.existsById(customerId);
        if (!exists) {
            throw new IllegalStateException("Customer id (" + customerId + ") does not exist.");
        }
        customerRepository.deleteById(customerId);
    }

    @Transactional
    public void updateCustomer(Long customerId, String firstName, String lastName, String email) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new IllegalStateException("Customer with id (" + customerId + ") does not exist."));

        if (firstName != null && !firstName.isEmpty() && !Objects.equals(customer.getFirstName(), firstName)) {
            customer.setFirstName(firstName);
        }
        if (lastName != null && !lastName.isEmpty() && !Objects.equals(customer.getLastName(), lastName)) {
            customer.setLastName(lastName);
        }
        if (email != null && !email.isEmpty() && !Objects.equals(customer.getEmail(), email)) {
            Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(email);
            if (customerOptional.isPresent()) {
                throw new IllegalStateException("Email already in use.");
            }
            customer.setEmail(email);
        }
    }
}
