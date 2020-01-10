package com.lyaev.accounts.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "OPERATIONS")
public class OperationsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    public Long id;

    @Column(name = "ACCOUNT_ID")
    public Long account_id;

    /**
     * All operations are saved to database in coin! To avoid rounding errors.
     */
    @Column(name = "SUMM_DEBIT")
    public Long summDebit;

    /**
     * All operations are saved to database in coin! To avoid rounding errors.
     */
    @Column(name = "SUMM_CREDIT")
    public Long summCredit;
}
