package com.bank.portal.service;

import com.bank.portal.dto.ApiResponse;
import com.bank.portal.model.Employee;
import com.bank.portal.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuditService auditService;
    
    @Transactional
    public ApiResponse createEmployee(Employee employee, String performedBy) {
        try {
            if (employeeRepository.existsByEmail(employee.getEmail())) {
                return new ApiResponse(false, "Email already exists");
            }
            
            // Generate employee ID
            employee.setEmployeeId("EMP" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
            
            // Encode password
            employee.setPassword(passwordEncoder.encode(employee.getPassword()));
            
            Employee saved = employeeRepository.save(employee);
            
            auditService.logAction(
                    "CREATE_EMPLOYEE",
                    performedBy,
                    "Employee",
                    saved.getId().toString(),
                    "Created employee: " + saved.getEmployeeId()
            );
            
            return new ApiResponse(true, "Employee created successfully", saved);
        } catch (Exception e) {
            return new ApiResponse(false, "Error creating employee: " + e.getMessage());
        }
    }
    
    @Transactional
    public ApiResponse updateEmployee(Long id, Employee employee, String performedBy) {
        try {
            Employee existing = employeeRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Employee not found"));
            
            existing.setFirstName(employee.getFirstName());
            existing.setLastName(employee.getLastName());
            existing.setPhone(employee.getPhone());
            existing.setDepartment(employee.getDepartment());
            existing.setDesignation(employee.getDesignation());
            
            if (employee.getRole() != null) {
                existing.setRole(employee.getRole());
            }
            if (employee.getStatus() != null) {
                existing.setStatus(employee.getStatus());
            }
            
            Employee updated = employeeRepository.save(existing);
            
            auditService.logAction(
                    "UPDATE_EMPLOYEE",
                    performedBy,
                    "Employee",
                    id.toString(),
                    "Updated employee: " + updated.getEmployeeId()
            );
            
            return new ApiResponse(true, "Employee updated successfully", updated);
        } catch (Exception e) {
            return new ApiResponse(false, "Error updating employee: " + e.getMessage());
        }
    }
    
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }
    
    public Employee getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email).orElse(null);
    }
    
    @Transactional
    public ApiResponse changeEmployeeStatus(Long id, Employee.EmployeeStatus status, String performedBy) {
        try {
            Employee employee = employeeRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Employee not found"));
            
            employee.setStatus(status);
            employeeRepository.save(employee);
            
            auditService.logAction(
                    "CHANGE_EMPLOYEE_STATUS",
                    performedBy,
                    "Employee",
                    id.toString(),
                    "Changed status to: " + status
            );
            
            return new ApiResponse(true, "Employee status updated successfully");
        } catch (Exception e) {
            return new ApiResponse(false, "Error updating status: " + e.getMessage());
        }
    }
    
    @Transactional
    public ApiResponse deleteEmployee(Long id, String performedBy) {
        try {
            Employee employee = employeeRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Employee not found"));
            
            String employeeId = employee.getEmployeeId();
            employeeRepository.delete(employee);
            
            auditService.logAction(
                    "DELETE_EMPLOYEE",
                    performedBy,
                    "Employee",
                    id.toString(),
                    "Deleted employee: " + employeeId
            );
            
            return new ApiResponse(true, "Employee deleted successfully");
        } catch (Exception e) {
            return new ApiResponse(false, "Error deleting employee: " + e.getMessage());
        }
    }
}
