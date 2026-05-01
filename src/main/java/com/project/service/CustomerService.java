package com.project.service;

import com.project.entity.Customer;
import com.project.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service class for Customer-related business logic.
 */
@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Retrieve all customers from the database.
     */
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    /**
     * Find a customer by ID.
     */
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    /**
     * Save a new customer to the database.
     * Throws DataIntegrityViolationException if email already exists.
     */
    public Customer saveCustomer(Customer customer) {
        if (customer.getId() == null && customerRepository.existsByEmail(customer.getEmail())) {
            throw new DataIntegrityViolationException(
                    "A customer with email '" + customer.getEmail() + "' already exists.");
        }
        return customerRepository.save(customer);
    }

    /**
     * Update an existing customer.
     */
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Customer existing = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

        existing.setName(updatedCustomer.getName());
        existing.setEmail(updatedCustomer.getEmail());
        existing.setPhone(updatedCustomer.getPhone());
        existing.setAddress(updatedCustomer.getAddress());

        return customerRepository.save(existing);
    }

    /**
     * Delete a customer by ID.
     */
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
