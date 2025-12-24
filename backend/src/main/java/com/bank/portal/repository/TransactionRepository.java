package com.bank.portal.repository;

import com.bank.portal.model.Account;
import com.bank.portal.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findByTransactionId(String transactionId);
    List<Transaction> findByAccount(Account account);
    List<Transaction> findByAccountId(Long accountId);
    List<Transaction> findByAccountIdOrderByCreatedAtDesc(Long accountId);
    List<Transaction> findByType(Transaction.TransactionType type);
    List<Transaction> findByStatus(Transaction.TransactionStatus status);
    List<Transaction> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
    List<Transaction> findByAccountIdAndCreatedAtBetween(Long accountId, LocalDateTime start, LocalDateTime end);
}
