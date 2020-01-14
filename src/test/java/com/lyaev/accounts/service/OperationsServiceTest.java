package com.lyaev.accounts.service;

import com.lyaev.accounts.model.AccountEntity;
import com.lyaev.accounts.model.OperationJSON;
import com.lyaev.accounts.model.OperationsEntity;
import com.lyaev.accounts.repository.AccountRepository;
import com.lyaev.accounts.repository.OperationsRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class OperationsServiceTest {
    @MockBean
    AccountRepository accountRepository;
    @MockBean
    OperationsRepository operationsRepository;

    @Autowired
    OperationsService operationsService;

    private OperationJSON createTestOperationJSON(){
        OperationJSON operation = new OperationJSON();
        operation.setAccountName("testAccount");
        operation.setIsDebit((byte) 1);
        operation.setSumm(new BigDecimal("100.0"));
        return operation;
    }

    private AccountEntity createTestAccount(){
        AccountEntity account = new AccountEntity();
        account.setName("testAccount");
        account.setAmount(new BigDecimal("0.0"));
        return account;
    }
    private OperationsEntity createTestOperation(){
        OperationsEntity operation = new OperationsEntity();
        operation.setAccountEntity(createTestAccount());
        operation.setSummCredit(new BigDecimal("100"));
        return operation;
    }

    @Test
    void addOperation() {
        OperationJSON operation = createTestOperationJSON();
        OperationsEntity operationsEntity = createTestOperation();
        AccountEntity accountEntity = createTestAccount();
        when(accountRepository.findByName(any(String.class))).thenReturn(accountEntity);
        when(operationsRepository.save(any(OperationsEntity.class))).thenReturn(operationsEntity);
        Assert.assertEquals(operationsService.addOperation(operation), operationsEntity);
    }

    @Test
    void getAllOperationsByAccountName() {
        List<OperationsEntity> operations = new ArrayList<>();
        operations.add(createTestOperation());
        when(operationsRepository.findAllByAccountEntity_Name(any(String.class))).thenReturn(operations);
        Assert.assertEquals(operationsService.getAllOperationsByAccountName("testAccount"), operations);
    }
}