package com.lyaev.accounts.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OperationJSON {
    private String accountName;
    private BigDecimal summ;
    private byte isDebit;
}
