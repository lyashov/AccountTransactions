package com.lyaev.accounts.repository;

import com.lyaev.accounts.model.AccountEntity;
import com.lyaev.accounts.model.OperationsEntity;
import org.junit.Assert;
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

    private AccountEntity createTestAccount(){
        AccountEntity account = new AccountEntity();
        account.setName("testAccount");
        account.setAmount(new BigDecimal("100.5"));
        return account;
    }

    private OperationsEntity createTestOperation(){
        OperationsEntity operation = new OperationsEntity();
        operation.setAccountEntity(createTestAccount());
        operation.setSummCredit(new BigDecimal("100.5"));
        return operation;
    }

    @Test
    @Transactional
    void findAllByAccountEntity_Name() {
        AccountEntity account = createTestAccount();
        AccountEntity accountFromBase = accountRepository.save(account);
        Assert.assertEquals(account, accountFromBase);
        accountRepository.delete(accountFromBase);


        OperationsEntity operation = createTestOperation();
        OperationsEntity operationFromBase = operationsRepository.save(operation);
        Assert.assertEquals(operation, operationFromBase);
        operationsRepository.delete(operationFromBase);
    }
}