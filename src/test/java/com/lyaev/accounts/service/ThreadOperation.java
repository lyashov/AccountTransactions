package com.lyaev.accounts.service;

import com.lyaev.accounts.model.AccountEntity;
import com.lyaev.accounts.model.OperationJSON;
import com.lyaev.accounts.model.OperationsEntity;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class ThreadOperation implements Runnable{
    @Autowired
    OperationsService operationsService;

    @Autowired
    AccountService accountService;

    AccountEntity account = new AccountEntity();
    OperationJSON operationJSON = new OperationJSON();
    OperationsEntity operation = new OperationsEntity();

    @Before
    private void BeforeTests(){
        final String ACCOUNT_NAME = "testAccount";
        final byte IS_DEBIT = 1;
        BigDecimal initSum = new BigDecimal("0.0");

        account.setName(ACCOUNT_NAME);
        account.setAmount(initSum);

        operationJSON.setAccountName(ACCOUNT_NAME);
        operationJSON.setIsDebit(IS_DEBIT);
        operationJSON.setSumm(initSum);

        operation.setAccountEntity(account);
        operation.setSummCredit(initSum);
    }

   /* ThreadOperation(){
        OperationJSON operationsJson = createTestOperationJSON();
        OperationsEntity operation = operationsService.addOperation(operationsJson);
        accountService.updateAmount(operationsJson.getAccountName());
    }*/

    @Override
    public void run() {

    }
}
