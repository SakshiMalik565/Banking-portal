package com.bank.portal.dto;

import com.bank.portal.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
    private Long id;
    private String transactionId;
    private AccountSummary account;
    private String transactionType;
    private BigDecimal amount;
    private BigDecimal balanceAfter;
    private String description;
    private String status;
    private String performedBy;
    private LocalDateTime timestamp;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AccountSummary {
        private Long id;
        private String accountNumber;
        private String accountType;
    }
    
    public static TransactionDTO fromTransaction(Transaction transaction) {
        TransactionDTO dto = new TransactionDTO();
        dto.setId(transaction.getId());
        dto.setTransactionId(transaction.getTransactionId());
        
        if (transaction.getAccount() != null) {
            AccountSummary accountSummary = new AccountSummary();
            accountSummary.setId(transaction.getAccount().getId());
            accountSummary.setAccountNumber(transaction.getAccount().getAccountNumber());
            accountSummary.setAccountType(transaction.getAccount().getAccountType().name());
            dto.setAccount(accountSummary);
        }
        
        dto.setTransactionType(transaction.getType().name());
        dto.setAmount(transaction.getAmount());
        dto.setBalanceAfter(transaction.getBalanceAfter());
        dto.setDescription(transaction.getDescription());
        dto.setStatus(transaction.getStatus().name());
        dto.setPerformedBy(transaction.getPerformedBy());
        dto.setTimestamp(transaction.getCreatedAt());
        
        return dto;
    }
}
