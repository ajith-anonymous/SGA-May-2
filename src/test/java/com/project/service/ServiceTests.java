package com.project.service;

import com.project.entity.Customer;
import com.project.entity.Order;
import com.project.repository.CustomerRepository;
import com.project.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Unit tests for CustomerService and OrderService using Mockito.
 */
@ExtendWith(MockitoExtension.class)
class ServiceTests {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private CustomerService customerService;

    @InjectMocks
    private OrderService orderService;

    private Customer testCustomer;
    private Order testOrder;

    @BeforeEach
    void setUp() {
        testCustomer = new Customer("Rajesh Kumar", "rajesh@email.com", "9876543210", "Bangalore");
        testCustomer.setId(1L);

        testOrder = new Order("Laptop", 1, 75000.0, LocalDate.of(2026, 4, 15), "Processing", testCustomer);
        testOrder.setId(1L);
    }

    // ── CustomerService Tests ─────────────────────────────────

    @Test
    @DisplayName("Should return all customers")
    void testGetAllCustomers() {
        Customer c2 = new Customer("Priya", "priya@email.com", "1111111111", "Chennai");
        c2.setId(2L);
        when(customerRepository.findAll()).thenReturn(Arrays.asList(testCustomer, c2));

        List<Customer> customers = customerService.getAllCustomers();

        assertThat(customers).hasSize(2);
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should find customer by ID")
    void testGetCustomerById() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(testCustomer));

        Optional<Customer> found = customerService.getCustomerById(1L);

        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Rajesh Kumar");
    }

    @Test
    @DisplayName("Should save customer successfully")
    void testSaveCustomer() {
        Customer newCust = new Customer("New User", "new@email.com", "5555555555", "Delhi");
        when(customerRepository.existsByEmail("new@email.com")).thenReturn(false);
        when(customerRepository.save(any(Customer.class))).thenReturn(newCust);

        Customer saved = customerService.saveCustomer(newCust);

        assertThat(saved.getName()).isEqualTo("New User");
        verify(customerRepository).save(newCust);
    }

    @Test
    @DisplayName("Should throw exception for duplicate email")
    void testSaveCustomerDuplicateEmail() {
        Customer duplicate = new Customer("Dup", "rajesh@email.com", "0000000000", "Addr");
        when(customerRepository.existsByEmail("rajesh@email.com")).thenReturn(true);

        assertThatThrownBy(() -> customerService.saveCustomer(duplicate))
                .isInstanceOf(DataIntegrityViolationException.class)
                .hasMessageContaining("already exists");
    }

    @Test
    @DisplayName("Should update customer successfully")
    void testUpdateCustomer() {
        Customer updated = new Customer("Updated Name", "rajesh@email.com", "9876543210", "Mumbai");
        when(customerRepository.findById(1L)).thenReturn(Optional.of(testCustomer));
        when(customerRepository.save(any(Customer.class))).thenReturn(testCustomer);

        Customer result = customerService.updateCustomer(1L, updated);

        assertThat(result).isNotNull();
        verify(customerRepository).save(any(Customer.class));
    }

    @Test
    @DisplayName("Should throw exception when updating non-existing customer")
    void testUpdateNonExistingCustomer() {
        when(customerRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> customerService.updateCustomer(999L, testCustomer))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("not found");
    }

    // ── OrderService Tests ────────────────────────────────────

    @Test
    @DisplayName("Should return all orders")
    void testGetAllOrders() {
        when(orderRepository.findAll()).thenReturn(Arrays.asList(testOrder));

        List<Order> orders = orderService.getAllOrders();

        assertThat(orders).hasSize(1);
        verify(orderRepository).findAll();
    }

    @Test
    @DisplayName("Should save order successfully")
    void testSaveOrder() {
        when(orderRepository.save(any(Order.class))).thenReturn(testOrder);

        Order saved = orderService.saveOrder(testOrder);

        assertThat(saved.getProductName()).isEqualTo("Laptop");
        verify(orderRepository).save(testOrder);
    }

    @Test
    @DisplayName("Should find order by ID")
    void testGetOrderById() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(testOrder));

        Optional<Order> found = orderService.getOrderById(1L);

        assertThat(found).isPresent();
        assertThat(found.get().getProductName()).isEqualTo("Laptop");
    }

    @Test
    @DisplayName("Should update order successfully")
    void testUpdateOrder() {
        Order updated = new Order("Updated Product", 3, 90000.0, LocalDate.now(), "Delivered", testCustomer);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(testOrder));
        when(orderRepository.save(any(Order.class))).thenReturn(testOrder);

        Order result = orderService.updateOrder(1L, updated);

        assertThat(result).isNotNull();
        verify(orderRepository).save(any(Order.class));
    }

    @Test
    @DisplayName("Should get orders with customers via INNER JOIN")
    void testGetAllOrdersWithCustomers() {
        when(orderRepository.findAllOrdersWithCustomers()).thenReturn(Arrays.asList(testOrder));

        List<Order> orders = orderService.getAllOrdersWithCustomers();

        assertThat(orders).hasSize(1);
        assertThat(orders.get(0).getCustomer()).isNotNull();
        verify(orderRepository).findAllOrdersWithCustomers();
    }

    @Test
    @DisplayName("Should find orders by customer ID")
    void testGetOrdersByCustomerId() {
        when(orderRepository.findByCustomerId(1L)).thenReturn(Arrays.asList(testOrder));

        List<Order> orders = orderService.getOrdersByCustomerId(1L);

        assertThat(orders).hasSize(1);
        verify(orderRepository).findByCustomerId(1L);
    }

    @Test
    @DisplayName("Should find orders by customer name")
    void testGetOrdersByCustomerName() {
        when(orderRepository.findOrdersByCustomerName("Rajesh")).thenReturn(Arrays.asList(testOrder));

        List<Order> orders = orderService.getOrdersByCustomerName("Rajesh");

        assertThat(orders).hasSize(1);
        verify(orderRepository).findOrdersByCustomerName("Rajesh");
    }
}
