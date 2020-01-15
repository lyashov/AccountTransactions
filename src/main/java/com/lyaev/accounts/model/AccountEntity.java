package com.lyaev.accounts.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@Data
@Entity
@Table(name = "ACCOUNT")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "AMOUNT", precision = 19, scale = 6)
    private BigDecimal amount;

    @Column(name = "DATE_OPEN")
    private Date dateOpen;
}
