package com.rishubank.account_management_service.controller;


import com.rishubank.account_management_service.dto.AccountDTO;
import com.rishubank.account_management_service.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO dto) {
        AccountDTO created = accountService.createAccount(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable Long accountId) {
        AccountDTO account = accountService.getAccountById(accountId);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/number/{accountNumber}")
    public ResponseEntity<AccountDTO> getAccountByNumber(@PathVariable String accountNumber) {
        AccountDTO account = accountService.getAccountByNumber(accountNumber);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<AccountDTO>> getCustomerAccounts(@PathVariable Long customerId) {
        List<AccountDTO> accounts = accountService.getAccountsByCustomerId(customerId);
        return ResponseEntity.ok(accounts);
    }

    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        List<AccountDTO> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<AccountDTO> updateAccount(
            @PathVariable Long accountId,
            @RequestBody AccountDTO dto) {
        AccountDTO updated = accountService.updateAccount(accountId, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long accountId) {
        accountService.deleteAccount(accountId);
        return ResponseEntity.noContent().build();
    }

}

