package com.bank.portal.controller;

import com.bank.portal.dto.ApiResponse;
import com.bank.portal.model.Account;
import com.bank.portal.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AccountController {
    
    private final AccountService accountService;
    
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'CLERK')")
    public ResponseEntity<ApiResponse> createAccount(
            @RequestBody Account account,
            @RequestParam Long customerId,
            Authentication auth) {
        ApiResponse response = accountService.createAccount(account, customerId, auth.getName());
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'CLERK')")
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }
    
    @GetMapping("/customer/{customerId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'CLERK')")
    public ResponseEntity<List<Account>> getAccountsByCustomerId(@PathVariable Long customerId) {
        return ResponseEntity.ok(accountService.getAccountsByCustomerId(customerId));
    }
    
    @GetMapping("/number/{accountNumber}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'CLERK')")
    public ResponseEntity<Account> getAccountByNumber(@PathVariable String accountNumber) {
        Account account = accountService.getAccountByNumber(accountNumber);
        if (account != null) {
            return ResponseEntity.ok(account);
        }
        return ResponseEntity.notFound().build();
    }
    
    @PostMapping("/deposit")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'CLERK')")
    public ResponseEntity<ApiResponse> deposit(
            @RequestBody Map<String, Object> request,
            Authentication auth) {
        String accountNumber = (String) request.get("accountNumber");
        BigDecimal amount = new BigDecimal(request.get("amount").toString());
        ApiResponse response = accountService.deposit(accountNumber, amount, auth.getName());
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/withdraw")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'CLERK')")
    public ResponseEntity<ApiResponse> withdraw(
            @RequestBody Map<String, Object> request,
            Authentication auth) {
        String accountNumber = (String) request.get("accountNumber");
        BigDecimal amount = new BigDecimal(request.get("amount").toString());
        ApiResponse response = accountService.withdraw(accountNumber, amount, auth.getName());
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/transfer")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'CLERK')")
    public ResponseEntity<ApiResponse> transfer(
            @RequestBody Map<String, Object> request,
            Authentication auth) {
        String fromAccountNumber = (String) request.get("fromAccountNumber");
        String toAccountNumber = (String) request.get("toAccountNumber");
        BigDecimal amount = new BigDecimal(request.get("amount").toString());
        ApiResponse response = accountService.transfer(fromAccountNumber, toAccountNumber, amount, auth.getName());
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/{accountNumber}/toggle-status")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<ApiResponse> toggleAccountStatus(
            @PathVariable String accountNumber,
            Authentication auth) {
        ApiResponse response = accountService.toggleAccountStatus(accountNumber, auth.getName());
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{accountNumber}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'CLERK')")
    public ResponseEntity<ApiResponse> deleteAccount(
            @PathVariable String accountNumber,
            Authentication auth) {
        ApiResponse response = accountService.deleteAccount(accountNumber, auth.getName());
        return ResponseEntity.ok(response);
    }
}
