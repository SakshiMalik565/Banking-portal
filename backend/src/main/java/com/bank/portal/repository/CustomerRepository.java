package com.bank.portal.repository;

import com.bank.portal.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByCustomerId(String customerId);
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByPanNumber(String panNumber);
    Optional<Customer> findByAadharNumber(String aadharNumber);
    List<Customer> findByStatus(Customer.CustomerStatus status);
    boolean existsByCustomerId(String customerId);
    boolean existsByEmail(String email);
    boolean existsByPanNumber(String panNumber);
    boolean existsByAadharNumber(String aadharNumber);
}
