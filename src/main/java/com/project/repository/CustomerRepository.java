package com.project.repository;

import com.project.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for Customer entity.
 * Extends JpaRepository for standard CRUD operations.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /**
     * Find a customer by email address.
     */
    Optional<Customer> findByEmail(String email);

    /**
     * Check if a customer exists with the given email.
     */
    boolean existsByEmail(String email);
}
