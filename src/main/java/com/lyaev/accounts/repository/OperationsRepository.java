package com.lyaev.accounts.repository;

import com.lyaev.accounts.model.OperationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface OperationsRepository extends JpaRepository<OperationsEntity, Long> {
    List<OperationsEntity> findAllByAccountEntity_Name(String accountName);

    @Query("SELECT SUM(op.summDebit) FROM OperationsEntity op WHERE op.accountEntity.name =:accountName")
    BigDecimal summDebit(@Param("accountName") String accountName);

    @Query("SELECT SUM(op.summCredit) FROM OperationsEntity op WHERE op.accountEntity.name =:accountName")
    BigDecimal summCredit(@Param("accountName") String accountName);

}
