package com.lyaev.accounts.service;

import com.lyaev.accounts.model.AccountEntity;
import com.lyaev.accounts.repository.AccountRepository;
import com.lyaev.accounts.repository.OperationsRepository;
import org.junit.Assert;
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
class AccountServiceTest {
    @MockBean
    AccountRepository accountRepository;
    @MockBean
    OperationsRepository operationsRepository;

    @Autowired
    AccountService accountService;

    AccountEntity account = new AccountEntity();
    @BeforeEach
    public void BeforeTests(){
        final String ACCOUNT_NAME = "testAccount";
        BigDecimal initSum = new BigDecimal("0.0");

        account.setName(ACCOUNT_NAME);
        account.setAmount(initSum);
    }

    @Test
    void createOrUpdateAccount() {
        when(accountRepository.findByName(any(String.class))).thenReturn(account);
        when(accountRepository.save(any(AccountEntity.class))).thenReturn(account);
        Assert.assertEquals(accountService.createOrUpdateAccount(account), account);
    }

    @Test
    void getAllAccounts() {
        List<AccountEntity> accounts = new ArrayList<>();
        accounts.add(account);
        when(accountRepository.findAll()).thenReturn(accounts);
        Assert.assertEquals(accountService.getAllAccounts(), accounts);
    }
}