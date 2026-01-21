package com.rishubank.account_management_service.repository;


import com.rishubank.account_management_service.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    Optional<AccountEntity> findByAccountNumber(String accountNumber);
    List<AccountEntity> findByCustomerId(Long customerId);
    List<AccountEntity> findByIsActive(Boolean isActive);
}

