package com.project.controller;

import com.project.entity.Customer;
import com.project.service.CustomerService;
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
 * Spring MVC Controller for handling Customer-related HTTP requests.
 */
@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * READ: Display list of all customers.
     */
    @GetMapping("/list")
    public String listCustomers(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "customer-list";
    }

    /**
     * CREATE: Show the add customer form.
     */
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer-add";
    }

    /**
     * CREATE: Handle form submission to save a new customer.
     */
    @PostMapping("/add")
    public String addCustomer(@Valid @ModelAttribute("customer") Customer customer,
                              BindingResult result,
                              RedirectAttributes redirectAttributes,
                              Model model) {
        if (result.hasErrors()) {
            return "customer-add";
        }

        try {
            customerService.saveCustomer(customer);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Customer '" + customer.getName() + "' added successfully!");
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("errorMessage",
                    "Error: " + ex.getMessage());
            return "customer-add";
        }

        return "redirect:/customers/list";
    }

    /**
     * UPDATE: Show the edit customer form.
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model,
                               RedirectAttributes redirectAttributes) {
        return customerService.getCustomerById(id)
                .map(customer -> {
                    model.addAttribute("customer", customer);
                    return "customer-update";
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute("errorMessage",
                            "Customer not found with ID: " + id);
                    return "redirect:/customers/list";
                });
    }

    /**
     * UPDATE: Handle form submission to update an existing customer.
     */
    @PostMapping("/update/{id}")
    public String updateCustomer(@PathVariable Long id,
                                 @Valid @ModelAttribute("customer") Customer customer,
                                 BindingResult result,
                                 RedirectAttributes redirectAttributes,
                                 Model model) {
        if (result.hasErrors()) {
            customer.setId(id);
            return "customer-update";
        }

        try {
            customerService.updateCustomer(id, customer);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Customer updated successfully!");
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("errorMessage",
                    "Error: " + ex.getMessage());
            customer.setId(id);
            return "customer-update";
        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
            return "redirect:/customers/list";
        }

        return "redirect:/customers/list";
    }
}
