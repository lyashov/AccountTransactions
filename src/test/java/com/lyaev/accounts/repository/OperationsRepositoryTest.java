package com.lyaev.accounts.repository;

import com.lyaev.accounts.model.AccountEntity;
import com.lyaev.accounts.model.OperationJSON;
import com.lyaev.accounts.model.OperationsEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class OperationsRepositoryTest {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    OperationsRepository operationsRepository;

    AccountEntity account = new AccountEntity();
    OperationsEntity operation = new OperationsEntity();

    @BeforeEach
    public void BeforeTests(){
        final String ACCOUNT_NAME = "testAccount";
        BigDecimal initSum = new BigDecimal("0.0");

        account.setName(ACCOUNT_NAME);
        account.setAmount(initSum);

        operation.setAccountEntity(account);
        operation.setSummCredit(initSum);
    }

    @Test
    @Transactional
    void findAllByAccountEntity_Name() {
        AccountEntity accountFromBase = accountRepository.save(account);
        Assert.assertEquals(account, accountFromBase);
        accountRepository.delete(accountFromBase);

        OperationsEntity operationFromBase = operationsRepository.save(operation);
        Assert.assertEquals(operation, operationFromBase);
        operationsRepository.delete(operationFromBase);
    }
}