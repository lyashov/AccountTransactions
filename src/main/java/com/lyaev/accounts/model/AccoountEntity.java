package com.lyaev.accounts.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="ACCOUNT")
public class AccoountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="ID", nullable = false)
    public Long id;

    @Column(name = "NAME", nullable = false)
    public String name;

    @Column(name = "AMOUNT")
    public Long amount;

    @Column(name = "DATE_OPEN")
    public Date dateOpen;

    @Column(name = "DATE_CLOSED")
    public Date dateClosed;

}
