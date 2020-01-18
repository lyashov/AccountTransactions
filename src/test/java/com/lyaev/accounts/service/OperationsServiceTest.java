package com.lyaev.accounts.service;

import com.lyaev.accounts.model.AccountEntity;
import com.lyaev.accounts.model.OperationJSON;
import com.lyaev.accounts.model.OperationsEntity;
import com.lyaev.accounts.repository.AccountRepository;
import com.lyaev.accounts.repository.OperationsRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    AccountEntity account = new AccountEntity();
    OperationJSON operationJSON = new OperationJSON();
    OperationsEntity operation = new OperationsEntity();

    @BeforeEach
    public void BeforeTests(){
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

    @Test
    void addOperation() {
        when(accountRepository.findByName(any(String.class))).thenReturn(account);
        when(operationsRepository.save(any(OperationsEntity.class))).thenReturn(operation);
        Assert.assertEquals(operationsService.addOperation(operationJSON), operation);
    }

    @Test
    void getAllOperationsByAccountName() {
        List<OperationsEntity> operations = new ArrayList<>();
        operations.add(operation);
        when(operationsRepository.findAllByAccountEntity_Name(any(String.class))).thenReturn(operations);
        Assert.assertEquals(operationsService.getAllOperationsByAccountName("testAccount"), operations);
    }
}