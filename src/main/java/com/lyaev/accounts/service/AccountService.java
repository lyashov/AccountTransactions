package com.lyaev.accounts.service;

import com.lyaev.accounts.model.AccountEntity;
import com.lyaev.accounts.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public Long moneyToCoins(Double money){
        Double coins = money * 100;
        return coins.longValue();
    }

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
