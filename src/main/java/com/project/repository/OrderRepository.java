package com.project.repository;

import com.project.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Order entity.
 * Extends JpaRepository for standard CRUD operations.
 * Contains custom query methods including INNER JOIN between Orders and Customers.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Custom query: INNER JOIN between Orders and Customers.
     * Returns all orders along with their associated customer information.
     */
    @Query("SELECT o FROM Order o INNER JOIN FETCH o.customer c")
    List<Order> findAllOrdersWithCustomers();

    /**
     * Find all orders for a specific customer.
     */
    List<Order> findByCustomerId(Long customerId);

    /**
     * Custom query to find orders by customer name using INNER JOIN.
     */
    @Query("SELECT o FROM Order o INNER JOIN o.customer c WHERE c.name LIKE %:name%")
    List<Order> findOrdersByCustomerName(String name);
}
