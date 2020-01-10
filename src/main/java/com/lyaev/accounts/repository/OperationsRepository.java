package com.lyaev.accounts.repository;

import com.lyaev.accounts.model.OperationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationsRepository extends JpaRepository<OperationsEntity, Long> {
}
