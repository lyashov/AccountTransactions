package com.lyaev.accounts.repository;

import com.lyaev.accounts.model.OperationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationsRepository extends JpaRepository<OperationsEntity, Long> {
    List<OperationsEntity> findAllByAccountEntity_Name(String accountName);
}
