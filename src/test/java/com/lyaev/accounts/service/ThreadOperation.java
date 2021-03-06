package com.lyaev.accounts.service;

import com.lyaev.accounts.model.OperationJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Component
@Scope("prototype")
public class ThreadOperation implements Runnable{
    @Autowired
    OperationsService operationsService;

    @Autowired
    AccountService accountService;

    OperationJSON operationJSON = new OperationJSON();

    String accountName;
    byte isDebit;
    BigDecimal summ;

    public void setParameters(String accountName, byte isDebit, BigDecimal summ){
        this.accountName = accountName;
        this.isDebit = isDebit;
        this.summ = summ;
    }

    @Override
    @Transactional
    public void run() {
        operationJSON.setAccountName(accountName);
        operationJSON.setIsDebit(this.isDebit);
        operationJSON.setSumm(this.summ);
        operationJSON.setDateOperation("10/01/2020");

        operationsService.addOperation(operationJSON);
    }
}
