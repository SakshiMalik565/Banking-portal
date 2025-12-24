package com.bank.portal.repository;

import com.bank.portal.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);
    Optional<Employee> findByEmployeeId(String employeeId);
    List<Employee> findByRole(Employee.Role role);
    List<Employee> findByStatus(Employee.EmployeeStatus status);
    List<Employee> findByDepartment(String department);
    boolean existsByEmail(String email);
    boolean existsByEmployeeId(String employeeId);
}
