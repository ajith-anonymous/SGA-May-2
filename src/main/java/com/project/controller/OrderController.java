package com.project.controller;

import com.project.entity.Customer;
import com.project.entity.Order;
import com.project.service.CustomerService;
import com.project.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Spring MVC Controller for handling Order-related HTTP requests.
 */
@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final CustomerService customerService;

    @Autowired
    public OrderController(OrderService orderService, CustomerService customerService) {
        this.orderService = orderService;
        this.customerService = customerService;
    }

    /**
     * READ: Display list of all orders with customer info (INNER JOIN).
     */
    @GetMapping("/list")
    public String listOrders(Model model) {
        List<Order> orders = orderService.getAllOrdersWithCustomers();
        model.addAttribute("orders", orders);
        return "order-list";
    }

    /**
     * CREATE: Show the add order form.
     */
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("order", new Order());
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "order-add";
    }

    /**
     * CREATE: Handle form submission to save a new order.
     */
    @PostMapping("/add")
    public String addOrder(@Valid @ModelAttribute("order") Order order,
                           BindingResult result,
                           @RequestParam(value = "customerId", required = false) Long customerId,
                           RedirectAttributes redirectAttributes,
                           Model model) {
        if (result.hasErrors()) {
            model.addAttribute("customers", customerService.getAllCustomers());
            return "order-add";
        }

        if (customerId == null) {
            model.addAttribute("errorMessage", "Please select a customer.");
            model.addAttribute("customers", customerService.getAllCustomers());
            return "order-add";
        }

        try {
            Customer customer = customerService.getCustomerById(customerId)
                    .orElseThrow(() -> new RuntimeException("Customer not found"));
            order.setCustomer(customer);
            orderService.saveOrder(order);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Order for '" + order.getProductName() + "' added successfully!");
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("errorMessage", "Integrity error: " + ex.getMessage());
            model.addAttribute("customers", customerService.getAllCustomers());
            return "order-add";
        } catch (RuntimeException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("customers", customerService.getAllCustomers());
            return "order-add";
        }

        return "redirect:/orders/list";
    }

    /**
     * UPDATE: Show the edit order form.
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model,
                               RedirectAttributes redirectAttributes) {
        return orderService.getOrderById(id)
                .map(order -> {
                    model.addAttribute("order", order);
                    model.addAttribute("customers", customerService.getAllCustomers());
                    return "order-update";
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute("errorMessage",
                            "Order not found with ID: " + id);
                    return "redirect:/orders/list";
                });
    }

    /**
     * UPDATE: Handle form submission to update an existing order.
     */
    @PostMapping("/update/{id}")
    public String updateOrder(@PathVariable Long id,
                              @Valid @ModelAttribute("order") Order order,
                              BindingResult result,
                              @RequestParam(value = "customerId", required = false) Long customerId,
                              RedirectAttributes redirectAttributes,
                              Model model) {
        if (result.hasErrors()) {
            order.setId(id);
            model.addAttribute("customers", customerService.getAllCustomers());
            return "order-update";
        }

        if (customerId == null) {
            model.addAttribute("errorMessage", "Please select a customer.");
            order.setId(id);
            model.addAttribute("customers", customerService.getAllCustomers());
            return "order-update";
        }

        try {
            Customer customer = customerService.getCustomerById(customerId)
                    .orElseThrow(() -> new RuntimeException("Customer not found"));
            order.setCustomer(customer);
            orderService.updateOrder(id, order);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Order updated successfully!");
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("errorMessage", "Integrity error: " + ex.getMessage());
            order.setId(id);
            model.addAttribute("customers", customerService.getAllCustomers());
            return "order-update";
        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
            return "redirect:/orders/list";
        }

        return "redirect:/orders/list";
    }
}
