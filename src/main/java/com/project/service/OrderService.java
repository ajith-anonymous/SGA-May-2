package com.project.service;

import com.project.entity.Order;
import com.project.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service class for Order-related business logic.
 */
@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Retrieve all orders from the database.
     */
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Find an order by ID.
     */
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    /**
     * Save a new order to the database.
     */
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    /**
     * Update an existing order.
     */
    public Order updateOrder(Long id, Order updatedOrder) {
        Order existing = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        existing.setProductName(updatedOrder.getProductName());
        existing.setQuantity(updatedOrder.getQuantity());
        existing.setTotalPrice(updatedOrder.getTotalPrice());
        existing.setOrderDate(updatedOrder.getOrderDate());
        existing.setStatus(updatedOrder.getStatus());
        existing.setCustomer(updatedOrder.getCustomer());

        return orderRepository.save(existing);
    }

    /**
     * Delete an order by ID.
     */
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    /**
     * Custom query: Get all orders with customer info via INNER JOIN.
     */
    public List<Order> getAllOrdersWithCustomers() {
        return orderRepository.findAllOrdersWithCustomers();
    }

    /**
     * Find orders by customer ID.
     */
    public List<Order> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    /**
     * Find orders by customer name using INNER JOIN.
     */
    public List<Order> getOrdersByCustomerName(String name) {
        return orderRepository.findOrdersByCustomerName(name);
    }
}
