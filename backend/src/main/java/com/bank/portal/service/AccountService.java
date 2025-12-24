package com.bank.portal.service;

import com.bank.portal.dto.ApiResponse;
import com.bank.portal.model.Account;
import com.bank.portal.model.Customer;
import com.bank.portal.model.Transaction;
import com.bank.portal.repository.AccountRepository;
import com.bank.portal.repository.CustomerRepository;
import com.bank.portal.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {
    
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;
    private final AuditService auditService;
    
    @Transactional
    public ApiResponse createAccount(Account account, Long customerId, String performedBy) {
        try {
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new RuntimeException("Customer not found"));
            
            // Generate account number
            account.setAccountNumber(generateAccountNumber());
            account.setCustomer(customer);
            account.setBalance(BigDecimal.ZERO);
            account.setStatus(Account.AccountStatus.ACTIVE);
            account.setBranch("Main Branch");
            account.setIfscCode("BANK0001234");
            
            Account saved = accountRepository.save(account);
            
            auditService.logAction(
                    "CREATE_ACCOUNT",
                    performedBy,
                    "Account",
                    saved.getId().toString(),
                    "Created account: " + saved.getAccountNumber()
            );
            
            return new ApiResponse(true, "Account created successfully", saved);
        } catch (Exception e) {
            return new ApiResponse(false, "Error creating account: " + e.getMessage());
        }
    }
    
    @Transactional
    public ApiResponse deposit(String accountNumber, BigDecimal amount, String performedBy) {
        try {
            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                return new ApiResponse(false, "Amount must be greater than zero");
            }
            
            Account account = accountRepository.findByAccountNumber(accountNumber)
                    .orElseThrow(() -> new RuntimeException("Account not found"));
            
            if (account.getStatus() != Account.AccountStatus.ACTIVE) {
                return new ApiResponse(false, "Account is not active");
            }
            
            account.setBalance(account.getBalance().add(amount));
            accountRepository.save(account);
            
            // Create transaction record
            Transaction transaction = new Transaction();
            transaction.setTransactionId("TXN" + UUID.randomUUID().toString().substring(0, 12).toUpperCase());
            transaction.setAccount(account);
            transaction.setType(Transaction.TransactionType.DEPOSIT);
            transaction.setAmount(amount);
            transaction.setBalanceAfter(account.getBalance());
            transaction.setDescription("Deposit");
            transaction.setStatus(Transaction.TransactionStatus.SUCCESS);
            transaction.setPerformedBy(performedBy);
            transactionRepository.save(transaction);
            
            auditService.logAction(
                    "DEPOSIT",
                    performedBy,
                    "Account",
                    account.getId().toString(),
                    "Deposited: " + amount + " to account: " + accountNumber
            );
            
            return new ApiResponse(true, "Deposit successful", transaction);
        } catch (Exception e) {
            return new ApiResponse(false, "Error processing deposit: " + e.getMessage());
        }
    }
    
    @Transactional
    public ApiResponse withdraw(String accountNumber, BigDecimal amount, String performedBy) {
        try {
            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                return new ApiResponse(false, "Amount must be greater than zero");
            }
            
            Account account = accountRepository.findByAccountNumber(accountNumber)
                    .orElseThrow(() -> new RuntimeException("Account not found"));
            
            if (account.getStatus() != Account.AccountStatus.ACTIVE) {
                return new ApiResponse(false, "Account is not active");
            }
            
            if (account.getBalance().compareTo(amount) < 0) {
                return new ApiResponse(false, "Insufficient balance");
            }
            
            account.setBalance(account.getBalance().subtract(amount));
            accountRepository.save(account);
            
            // Create transaction record
            Transaction transaction = new Transaction();
            transaction.setTransactionId("TXN" + UUID.randomUUID().toString().substring(0, 12).toUpperCase());
            transaction.setAccount(account);
            transaction.setType(Transaction.TransactionType.WITHDRAWAL);
            transaction.setAmount(amount);
            transaction.setBalanceAfter(account.getBalance());
            transaction.setDescription("Withdrawal");
            transaction.setStatus(Transaction.TransactionStatus.SUCCESS);
            transaction.setPerformedBy(performedBy);
            transactionRepository.save(transaction);
            
            auditService.logAction(
                    "WITHDRAWAL",
                    performedBy,
                    "Account",
                    account.getId().toString(),
                    "Withdrew: " + amount + " from account: " + accountNumber
            );
            
            return new ApiResponse(true, "Withdrawal successful", transaction);
        } catch (Exception e) {
            return new ApiResponse(false, "Error processing withdrawal: " + e.getMessage());
        }
    }
    
    public List<Account> getAccountsByCustomerId(Long customerId) {
        return accountRepository.findByCustomerId(customerId);
    }
    
    public Account getAccountByNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber).orElse(null);
    }
    
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
    
    @Transactional
    public ApiResponse toggleAccountStatus(String accountNumber, String performedBy) {
        try {
            Account account = accountRepository.findByAccountNumber(accountNumber)
                    .orElseThrow(() -> new RuntimeException("Account not found"));
            
            if (account.getStatus() == Account.AccountStatus.ACTIVE) {
                account.setStatus(Account.AccountStatus.FROZEN);
            } else if (account.getStatus() == Account.AccountStatus.FROZEN) {
                account.setStatus(Account.AccountStatus.ACTIVE);
            }
            
            accountRepository.save(account);
            
            auditService.logAction(
                    "TOGGLE_ACCOUNT_STATUS",
                    performedBy,
                    "Account",
                    account.getId().toString(),
                    "Changed status to: " + account.getStatus()
            );
            
            return new ApiResponse(true, "Account status updated", account);
        } catch (Exception e) {
            return new ApiResponse(false, "Error updating account status: " + e.getMessage());
        }
    }
    
    @Transactional
    public ApiResponse transfer(String fromAccountNumber, String toAccountNumber, BigDecimal amount, String performedBy) {
        try {
            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                return new ApiResponse(false, "Amount must be greater than zero");
            }
            
            if (fromAccountNumber.equals(toAccountNumber)) {
                return new ApiResponse(false, "Cannot transfer to the same account");
            }
            
            Account fromAccount = accountRepository.findByAccountNumber(fromAccountNumber)
                    .orElseThrow(() -> new RuntimeException("Source account not found"));
            
            Account toAccount = accountRepository.findByAccountNumber(toAccountNumber)
                    .orElseThrow(() -> new RuntimeException("Destination account not found"));
            
            if (fromAccount.getStatus() != Account.AccountStatus.ACTIVE) {
                return new ApiResponse(false, "Source account is not active");
            }
            
            if (toAccount.getStatus() != Account.AccountStatus.ACTIVE) {
                return new ApiResponse(false, "Destination account is not active");
            }
            
            if (fromAccount.getBalance().compareTo(amount) < 0) {
                return new ApiResponse(false, "Insufficient balance");
            }
            
            // Debit from source account
            fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
            accountRepository.save(fromAccount);
            
            // Create debit transaction
            Transaction debitTxn = new Transaction();
            debitTxn.setTransactionId("TXN" + UUID.randomUUID().toString().substring(0, 12).toUpperCase());
            debitTxn.setAccount(fromAccount);
            debitTxn.setType(Transaction.TransactionType.TRANSFER);
            debitTxn.setAmount(amount.negate());
            debitTxn.setBalanceAfter(fromAccount.getBalance());
            debitTxn.setDescription("Transfer to " + toAccountNumber);
            debitTxn.setStatus(Transaction.TransactionStatus.SUCCESS);
            debitTxn.setPerformedBy(performedBy);
            transactionRepository.save(debitTxn);
            
            // Credit to destination account
            toAccount.setBalance(toAccount.getBalance().add(amount));
            accountRepository.save(toAccount);
            
            // Create credit transaction
            Transaction creditTxn = new Transaction();
            creditTxn.setTransactionId("TXN" + UUID.randomUUID().toString().substring(0, 12).toUpperCase());
            creditTxn.setAccount(toAccount);
            creditTxn.setType(Transaction.TransactionType.TRANSFER);
            creditTxn.setAmount(amount);
            creditTxn.setBalanceAfter(toAccount.getBalance());
            creditTxn.setDescription("Transfer from " + fromAccountNumber);
            creditTxn.setStatus(Transaction.TransactionStatus.SUCCESS);
            creditTxn.setPerformedBy(performedBy);
            transactionRepository.save(creditTxn);
            
            auditService.logAction(
                    "TRANSFER",
                    performedBy,
                    "Account",
                    fromAccount.getId().toString(),
                    "Transferred: " + amount + " from " + fromAccountNumber + " to " + toAccountNumber
            );
            
            return new ApiResponse(true, "Transfer successful", debitTxn);
        } catch (Exception e) {
            return new ApiResponse(false, "Error processing transfer: " + e.getMessage());
        }
    }

    @Transactional
    public ApiResponse deleteAccount(String accountNumber, String performedBy) {
        try {
            Account account = accountRepository.findByAccountNumber(accountNumber)
                    .orElseThrow(() -> new RuntimeException("Account not found"));
            
            // Check if account has balance
            if (account.getBalance().compareTo(BigDecimal.ZERO) > 0) {
                return new ApiResponse(false, "Cannot delete account with positive balance. Please withdraw all funds first.");
            }
            
            String accountId = account.getAccountNumber();
            accountRepository.delete(account);
            
            auditService.logAction(
                    "DELETE_ACCOUNT",
                    performedBy,
                    "Account",
                    account.getId().toString(),
                    "Deleted account: " + accountId
            );
            
            return new ApiResponse(true, "Account deleted successfully");
        } catch (Exception e) {
            return new ApiResponse(false, "Error deleting account: " + e.getMessage());
        }
    }
    
    private String generateAccountNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
