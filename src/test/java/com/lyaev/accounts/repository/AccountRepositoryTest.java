package com.lyaev.accounts.repository;

import com.lyaev.accounts.model.AccountEntity;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
class AccountRepositoryTest {
    @Autowired
    AccountRepository accountRepository;

    AccountEntity account;
    @BeforeEach
    public void BeforeTests(){
        account = new AccountEntity();
        final String ACCOUNT_NAME = "testAccount";
        BigDecimal initSum = new BigDecimal("0.0");

        account.setName(ACCOUNT_NAME);
        account.setAmount(initSum);
    }

    @Test
    @Transactional
    void findByName() {
        accountRepository.save(account);
        AccountEntity accountFromBase = accountRepository.findByName(account.getName());
        Assert.assertEquals(accountFromBase, account);
        accountRepository.delete(accountFromBase);
    }
}