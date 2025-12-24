package com.bank.portal.controller;

import com.bank.portal.dto.ApiResponse;
import com.bank.portal.model.Customer;
import com.bank.portal.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CustomerController {
    
    private final CustomerService customerService;
    
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'CLERK')")
    public ResponseEntity<ApiResponse> createCustomer(@RequestBody Customer customer, Authentication auth) {
        ApiResponse response = customerService.createCustomer(customer, auth.getName());
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'CLERK')")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'CLERK')")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/customerId/{customerId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'CLERK')")
    public ResponseEntity<Customer> getCustomerByCustomerId(@PathVariable String customerId) {
        Customer customer = customerService.getCustomerByCustomerId(customerId);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        }
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'CLERK')")
    public ResponseEntity<ApiResponse> updateCustomer(
            @PathVariable Long id,
            @RequestBody Customer customer,
            Authentication auth) {
        ApiResponse response = customerService.updateCustomer(id, customer, auth.getName());
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable Long id, Authentication auth) {
        ApiResponse response = customerService.deleteCustomer(id, auth.getName());
        return ResponseEntity.ok(response);
    }
}
