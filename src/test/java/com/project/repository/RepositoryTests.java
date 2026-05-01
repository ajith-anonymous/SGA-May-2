package com.project.repository;

import com.project.entity.Customer;
import com.project.entity.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for CustomerRepository and OrderRepository.
 * Uses @DataJpaTest for an in-memory H2 database slice.
 */
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class RepositoryTests {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    private Customer savedCustomer;

    @BeforeEach
    void setUp() {
        // Clear auto-loaded data
        orderRepository.deleteAll();
        customerRepository.deleteAll();

        // Create and save a test customer
        Customer customer = new Customer("Test User", "test@email.com", "1234567890", "Test Address");
        savedCustomer = customerRepository.save(customer);
    }

    // ── Customer Repository Tests ─────────────────────────────

    @Test
    @DisplayName("Should save a customer successfully")
    void testSaveCustomer() {
        Customer newCustomer = new Customer("John Doe", "john@email.com", "9999999999", "123 Main St");
        Customer saved = customerRepository.save(newCustomer);

        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo("John Doe");
    }

    @Test
    @DisplayName("Should find customer by ID")
    void testFindCustomerById() {
        Optional<Customer> found = customerRepository.findById(savedCustomer.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Test User");
    }

    @Test
    @DisplayName("Should find customer by email")
    void testFindCustomerByEmail() {
        Optional<Customer> found = customerRepository.findByEmail("test@email.com");

        assertThat(found).isPresent();
        assertThat(found.get().getEmail()).isEqualTo("test@email.com");
    }

    @Test
    @DisplayName("Should check if email exists")
    void testExistsByEmail() {
        boolean exists = customerRepository.existsByEmail("test@email.com");
        boolean notExists = customerRepository.existsByEmail("nope@email.com");

        assertThat(exists).isTrue();
        assertThat(notExists).isFalse();
    }

    @Test
    @DisplayName("Should return all customers")
    void testFindAllCustomers() {
        customerRepository.save(new Customer("Another", "another@email.com", "1111111111", "Addr"));

        List<Customer> customers = customerRepository.findAll();
        assertThat(customers).hasSizeGreaterThanOrEqualTo(2);
    }

    @Test
    @DisplayName("Should update customer")
    void testUpdateCustomer() {
        savedCustomer.setName("Updated Name");
        Customer updated = customerRepository.save(savedCustomer);

        assertThat(updated.getName()).isEqualTo("Updated Name");
    }

    // ── Order Repository Tests ────────────────────────────────

    @Test
    @DisplayName("Should save an order successfully")
    void testSaveOrder() {
        Order order = new Order("Laptop", 1, 75000.0, LocalDate.now(), "Processing", savedCustomer);
        Order saved = orderRepository.save(order);

        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getProductName()).isEqualTo("Laptop");
    }

    @Test
    @DisplayName("Should find orders by customer ID")
    void testFindOrdersByCustomerId() {
        orderRepository.save(new Order("Product A", 2, 5000.0, LocalDate.now(), "Shipped", savedCustomer));
        orderRepository.save(new Order("Product B", 1, 3000.0, LocalDate.now(), "Delivered", savedCustomer));

        List<Order> orders = orderRepository.findByCustomerId(savedCustomer.getId());
        assertThat(orders).hasSize(2);
    }

    @Test
    @DisplayName("Should find all orders with customers using INNER JOIN")
    void testFindAllOrdersWithCustomers() {
        orderRepository.save(new Order("Gadget X", 1, 10000.0, LocalDate.now(), "Processing", savedCustomer));

        List<Order> orders = orderRepository.findAllOrdersWithCustomers();
        assertThat(orders).isNotEmpty();
        assertThat(orders.get(0).getCustomer()).isNotNull();
    }

    @Test
    @DisplayName("Should find orders by customer name using INNER JOIN")
    void testFindOrdersByCustomerName() {
        orderRepository.save(new Order("Tablet", 1, 25000.0, LocalDate.now(), "Shipped", savedCustomer));

        List<Order> orders = orderRepository.findOrdersByCustomerName("Test");
        assertThat(orders).isNotEmpty();
        assertThat(orders.get(0).getCustomer().getName()).contains("Test");
    }

    @Test
    @DisplayName("Should update an order")
    void testUpdateOrder() {
        Order order = orderRepository.save(
                new Order("Old Product", 1, 1000.0, LocalDate.now(), "Processing", savedCustomer));

        order.setProductName("New Product");
        order.setStatus("Delivered");
        Order updated = orderRepository.save(order);

        assertThat(updated.getProductName()).isEqualTo("New Product");
        assertThat(updated.getStatus()).isEqualTo("Delivered");
    }
}
