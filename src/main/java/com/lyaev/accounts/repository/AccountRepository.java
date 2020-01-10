package com.lyaev.accounts.repository;

import com.lyaev.accounts.model.AccoountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccoountEntity, Long> {
}
