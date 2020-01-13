package com.lyaev.accounts.model;

import lombok.Data;

@Data
public class OperationJSON {
    private String accountName;
    private Double summ;
    private byte isDebit;
}
