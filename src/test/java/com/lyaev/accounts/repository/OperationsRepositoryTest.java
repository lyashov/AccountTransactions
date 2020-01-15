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
        accountRepository.save(account);

        OperationsEntity operation = createTestOperation();
        operationsRepository.save(operation);
        List<OperationsEntity> operations = operationsRepository.findAllByAccountEntity_Name(account.getName());
        Assert.assertTrue(operations.size() > 0);
        for (OperationsEntity op:operations) {
            operationsRepository.delete(op);
        }
    }
}