package com.lyaev.accounts.repository;

import com.lyaev.accounts.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    AccountEntity findByName(String name);
}
