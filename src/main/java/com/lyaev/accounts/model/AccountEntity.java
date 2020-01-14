package com.lyaev.accounts.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="ACCOUNT")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="ID", nullable = false)
    private Long id;

    @OneToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OperationsEntity> operations;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "DATE_OPEN")
    private Date dateOpen;

    @Column(name = "DATE_CLOSED")
    private Date dateClosed;

}
