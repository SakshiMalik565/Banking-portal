package com.bank.portal.repository;

import com.bank.portal.model.Account;
import com.bank.portal.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(String accountNumber);
    List<Account> findByCustomer(Customer customer);
    List<Account> findByCustomerId(Long customerId);
    List<Account> findByStatus(Account.AccountStatus status);
    List<Account> findByAccountType(Account.AccountType accountType);
    boolean existsByAccountNumber(String accountNumber);
}
