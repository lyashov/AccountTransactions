package com.lyaev.accounts.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "OPERATIONS")
public class OperationsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private AccountEntity accountEntity;

    @Column(name = "SUMM_DEBIT", precision = 19, scale = 6)
    private BigDecimal summDebit;

    @Column(name = "SUMM_CREDIT", precision = 19, scale = 6)
    private BigDecimal summCredit;
}
