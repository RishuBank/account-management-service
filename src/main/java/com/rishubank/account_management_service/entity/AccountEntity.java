package com.rishubank.account_management_service.entity;

import com.rishubank.account_management_service.constants.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    @Column(nullable = false, unique = true)
    private String accountNumber;

    @Column(nullable = false)
    private Long customerId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountType accountType; // SAVINGS, CHECKING, BUSINESS

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal balance;

    @Column(nullable = false)
    private String currency; // USD, INR, EUR

    @Column(nullable = false)
    private Boolean isActive = true;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;


}

