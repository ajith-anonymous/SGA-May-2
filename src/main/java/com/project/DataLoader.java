package com.project;

import com.project.entity.Customer;
import com.project.entity.Order;
import com.project.repository.CustomerRepository;
import com.project.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

/**
 * Data loader that populates the database with 10 customers and 10 orders
 * on application startup. Uses CommandLineRunner to run after JPA initialization.
 */
@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(CustomerRepository customerRepository,
                                   OrderRepository orderRepository) {
        return args -> {
            // Only load data if the database is empty
            if (customerRepository.count() > 0) {
                return;
            }

            // ── Insert 10 Customers ──────────────────────────────────
            Customer c1 = customerRepository.save(new Customer("Rajesh Kumar", "rajesh.kumar@email.com", "9876543210", "12 MG Road, Bangalore"));
            Customer c2 = customerRepository.save(new Customer("Priya Sharma", "priya.sharma@email.com", "9876543211", "45 Anna Nagar, Chennai"));
            Customer c3 = customerRepository.save(new Customer("Amit Patel", "amit.patel@email.com", "9876543212", "78 SG Highway, Ahmedabad"));
            Customer c4 = customerRepository.save(new Customer("Sneha Reddy", "sneha.reddy@email.com", "9876543213", "23 Jubilee Hills, Hyderabad"));
            Customer c5 = customerRepository.save(new Customer("Vikram Singh", "vikram.singh@email.com", "9876543214", "56 Connaught Place, Delhi"));
            Customer c6 = customerRepository.save(new Customer("Ananya Nair", "ananya.nair@email.com", "9876543215", "89 Marine Drive, Mumbai"));
            Customer c7 = customerRepository.save(new Customer("Karthik Iyer", "karthik.iyer@email.com", "9876543216", "34 Koramangala, Bangalore"));
            Customer c8 = customerRepository.save(new Customer("Divya Menon", "divya.menon@email.com", "9876543217", "67 Vyttila, Kochi"));
            Customer c9 = customerRepository.save(new Customer("Arjun Das", "arjun.das@email.com", "9876543218", "90 Salt Lake, Kolkata"));
            Customer c10 = customerRepository.save(new Customer("Meera Joshi", "meera.joshi@email.com", "9876543219", "12 FC Road, Pune"));

            // ── Insert 10 Orders ─────────────────────────────────────
            orderRepository.save(new Order("Laptop Dell XPS 15", 1, 125000.00, LocalDate.of(2026, 1, 15), "Delivered", c1));
            orderRepository.save(new Order("iPhone 16 Pro", 1, 134900.00, LocalDate.of(2026, 2, 10), "Shipped", c2));
            orderRepository.save(new Order("Samsung Galaxy S25", 2, 159800.00, LocalDate.of(2026, 2, 20), "Processing", c3));
            orderRepository.save(new Order("Sony WH-1000XM5", 3, 29970.00, LocalDate.of(2026, 3, 5), "Delivered", c4));
            orderRepository.save(new Order("MacBook Air M3", 1, 114900.00, LocalDate.of(2026, 3, 12), "Shipped", c5));
            orderRepository.save(new Order("iPad Pro 13 inch", 1, 112900.00, LocalDate.of(2026, 3, 18), "Processing", c6));
            orderRepository.save(new Order("Bose QC Ultra", 2, 49980.00, LocalDate.of(2026, 4, 1), "Delivered", c7));
            orderRepository.save(new Order("Canon EOS R6 Mark II", 1, 175000.00, LocalDate.of(2026, 4, 10), "Shipped", c8));
            orderRepository.save(new Order("Nintendo Switch 2", 2, 69980.00, LocalDate.of(2026, 4, 15), "Processing", c9));
            orderRepository.save(new Order("Kindle Paperwhite", 5, 74950.00, LocalDate.of(2026, 4, 20), "Delivered", c10));

            System.out.println("✅ Database populated with 10 customers and 10 orders.");
        };
    }
}
