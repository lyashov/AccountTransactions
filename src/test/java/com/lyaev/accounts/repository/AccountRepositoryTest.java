package com.lyaev.accounts.repository;

import com.lyaev.accounts.model.AccountEntity;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class AccountRepositoryTest {
    @Autowired
    AccountRepository accountRepository;

    private AccountEntity createTestAccount(){
        AccountEntity account = new AccountEntity();
        account.setName("testAccount");
        account.setAmount(new BigDecimal("100"));
        return account;
    }

    @Test
    void findByName() {
        AccountEntity account = createTestAccount();
        accountRepository.save(account);
        List<AccountEntity> accounts = accountRepository.findAll();
        Assert.assertTrue(accounts.size() > 0);
        for (AccountEntity acc:accounts) {
            accountRepository.delete(acc);
        }
    }
}