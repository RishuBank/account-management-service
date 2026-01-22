package com.rishubank.account_management_service.service;


import com.rishubank.account_management_service.dto.AccountDTO;
import com.rishubank.account_management_service.entity.AccountEntity;
import com.rishubank.account_management_service.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    public AccountDTO createAccount(AccountDTO dto) {
        AccountEntity account = new AccountEntity();
        account.setAccountNumber(dto.getAccountNumber());
        account.setCustomerId(dto.getCustomerId());
        account.setAccountType(dto.getAccountType());
        account.setBalance(dto.getBalance());
        account.setCurrency(dto.getCurrency());
        account.setIsActive(true);
        account.setCreatedDate(LocalDateTime.now());

        AccountEntity saved = accountRepository.save(account);
        return convertToDTO(saved);
    }

    public AccountDTO getAccountById(Long accountId) {
        AccountEntity account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        return convertToDTO(account);
    }

    public com.rishubank.account_management_service.dto.AccountDTO getAccountByNumber(String accountNumber) {
        AccountEntity account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        return convertToDTO(account);
    }

    public List<AccountDTO> getAccountsByCustomerId(Long customerId) {
        return accountRepository.findByCustomerId(customerId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<AccountDTO> getAllAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AccountDTO updateAccount(Long accountId, AccountDTO dto) {
        AccountEntity account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        account.setBalance(dto.getBalance());
        account.setIsActive(dto.getIsActive());
        account.setUpdatedDate(LocalDateTime.now());

        AccountEntity updated = accountRepository.save(account);
        return convertToDTO(updated);
    }

    public void deleteAccount(Long accountId) {
        accountRepository.deleteById(accountId);
    }

    private AccountDTO convertToDTO(AccountEntity account) {
        return new AccountDTO(
                account.getAccountId(),
                account.getAccountNumber(),
                account.getCustomerId(),
                account.getAccountType(),
                account.getBalance(),
                account.getCurrency(),
                account.getIsActive(),
                account.getCreatedDate(),
                account.getUpdatedDate()
        );
    }

}

