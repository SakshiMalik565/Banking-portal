package com.bank.portal.service;

import com.bank.portal.dto.ApiResponse;
import com.bank.portal.model.Customer;
import com.bank.portal.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {
    
    private final CustomerRepository customerRepository;
    private final AuditService auditService;
    
    @Transactional
    public ApiResponse createCustomer(Customer customer, String performedBy) {
        try {
            if (customerRepository.existsByEmail(customer.getEmail())) {
                return new ApiResponse(false, "Email already exists");
            }
            
            if (customer.getPanNumber() != null && customerRepository.existsByPanNumber(customer.getPanNumber())) {
                return new ApiResponse(false, "PAN number already exists");
            }
            
            if (customer.getAadharNumber() != null && customerRepository.existsByAadharNumber(customer.getAadharNumber())) {
                return new ApiResponse(false, "Aadhar number already exists");
            }
            
            // Generate customer ID
            customer.setCustomerId("CUS" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
            customer.setStatus(Customer.CustomerStatus.ACTIVE);
            
            Customer saved = customerRepository.save(customer);
            
            auditService.logAction(
                    "CREATE_CUSTOMER",
                    performedBy,
                    "Customer",
                    saved.getId().toString(),
                    "Created customer: " + saved.getCustomerId()
            );
            
            return new ApiResponse(true, "Customer created successfully", saved);
        } catch (Exception e) {
            return new ApiResponse(false, "Error creating customer: " + e.getMessage());
        }
    }
    
    @Transactional
    public ApiResponse updateCustomer(Long id, Customer customer, String performedBy) {
        try {
            Customer existing = customerRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Customer not found"));
            
            existing.setFirstName(customer.getFirstName());
            existing.setLastName(customer.getLastName());
            existing.setPhone(customer.getPhone());
            existing.setAddress(customer.getAddress());
            existing.setDateOfBirth(customer.getDateOfBirth());
            
            Customer updated = customerRepository.save(existing);
            
            auditService.logAction(
                    "UPDATE_CUSTOMER",
                    performedBy,
                    "Customer",
                    id.toString(),
                    "Updated customer: " + updated.getCustomerId()
            );
            
            return new ApiResponse(true, "Customer updated successfully", updated);
        } catch (Exception e) {
            return new ApiResponse(false, "Error updating customer: " + e.getMessage());
        }
    }
    
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }
    
    public Customer getCustomerByCustomerId(String customerId) {
        return customerRepository.findByCustomerId(customerId).orElse(null);
    }
    
    @Transactional
    public ApiResponse deleteCustomer(Long id, String performedBy) {
        try {
            Customer customer = customerRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Customer not found"));
            
            String customerId = customer.getCustomerId();
            customerRepository.delete(customer);
            
            auditService.logAction(
                    "DELETE_CUSTOMER",
                    performedBy,
                    "Customer",
                    id.toString(),
                    "Deleted customer: " + customerId
            );
            
            return new ApiResponse(true, "Customer deleted successfully");
        } catch (Exception e) {
            return new ApiResponse(false, "Error deleting customer: " + e.getMessage());
        }
    }
}
