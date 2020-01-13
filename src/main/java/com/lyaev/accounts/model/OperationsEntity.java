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

    @ManyToOne (optional=false, fetch = FetchType.EAGER)
    @JoinColumn (name="account_id",referencedColumnName="id")
    private AccountEntity accountEntity;

    /**
     * All operations are saved to database in coin! To avoid rounding errors.
     */
    @Column(name = "SUMM_DEBIT")
    private BigDecimal summDebit;

    /**
     * All operations are saved to database in coin! To avoid rounding errors.
     */
    @Column(name = "SUMM_CREDIT")
    private BigDecimal summCredit;
}
