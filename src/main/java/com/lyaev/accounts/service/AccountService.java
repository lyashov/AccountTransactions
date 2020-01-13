package com.lyaev.accounts.service;

import com.lyaev.accounts.model.AccountEntity;
import com.lyaev.accounts.repository.AccountRepository;
import com.lyaev.accounts.repository.OperationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    OperationsRepository operationsRepository;

    @Transactional
    public AccountEntity saveOrCreateAccount(AccountEntity account){
        if (account == null) return null;
        AccountEntity accountTemp = findByName(account.getName());
        if (accountTemp != null){
            accountTemp.setAmount(account.getAmount());
            accountTemp.setDateOpen(account.getDateOpen());
            return accountRepository.save(accountTemp);
        }
        return accountRepository.save(account);
    }

    public BigDecimal summDedit(String accountName) {
        BigDecimal amountDebit = operationsRepository.summDebit(accountName);
        return amountDebit == null ? new BigDecimal("0") : amountDebit;
    }

    public BigDecimal summCredit(String accountName) {
        BigDecimal amountCredit = operationsRepository.summCredit(accountName);
        return amountCredit == null ? new BigDecimal("0") : amountCredit;
    }

    @Transactional
    public void updateAmount(String accountName){
        if ((accountName == null)&&(accountName.equals(""))) return;
        AccountEntity account = findByName(accountName);
        if (account != null){
            BigDecimal amountDebit = summDedit(accountName);
            BigDecimal amountCredit = summCredit(accountName);
            account.setAmount(amountDebit.subtract(amountCredit));
            accountRepository.save(account);
        }
    }

    public AccountEntity findByName(String name){
        return accountRepository.findByName(name);
    }

    public List<AccountEntity> getAllAccounts(){
        List<AccountEntity> accounts = accountRepository.findAll();
        if(accounts.size() > 0) {
            return accounts;
        } else {
            return new ArrayList<AccountEntity>();
        }
    }

    @Transactional
    public void deleteAccount(String name){
        if ((name != null)&&(name != "")) {
            AccountEntity account = findByName(name);
            accountRepository.delete(account);
        }
    }
}