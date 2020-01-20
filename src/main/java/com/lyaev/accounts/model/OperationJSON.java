package com.lyaev.accounts.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OperationJSON {
    private String accountName;
    private String dateOperation;
    private BigDecimal summ;
    private byte isDebit;
}
