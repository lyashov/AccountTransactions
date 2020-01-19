package com.lyaev.accounts.service;

import com.lyaev.accounts.model.OperationJSON;
import com.lyaev.accounts.model.OperationsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ThreadOperation implements Runnable{
    @Autowired
    OperationsService operationsService;

    @Autowired
    AccountService accountService;

    OperationJSON operationJSON = new OperationJSON();

    String accountName;

    public void setAccount(String accountName){
        this.accountName = accountName;
    }

    @Override
    public void run() {
        final byte IS_DEBIT = 1;
        BigDecimal initSum = new BigDecimal("100");

        operationJSON.setAccountName(accountName);
        operationJSON.setIsDebit(IS_DEBIT);
        operationJSON.setSumm(initSum);

        OperationsEntity operation = operationsService.addOperation(operationJSON);

        accountService.updateAmount(accountName);
        BigDecimal accSum = accountService.getSummAccount(accountName);

        System.out.println(operation + "AAAAAAAAAAAAAAAAAAAAAA" + accSum.doubleValue());
    }
}
