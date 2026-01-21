package com.rishubank.account_management_service.dto;

import com.rishubank.account_management_service.constants.AccountType;
import com.rishubank.account_management_service.entity.AccountEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private Long accountId;
    private String accountNumber;
    private Long customerId;
    private AccountType accountType;
    private BigDecimal balance;
    private String currency;
    private Boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}

