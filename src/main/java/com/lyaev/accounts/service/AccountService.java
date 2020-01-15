package com.lyaev.accounts.service;

import com.lyaev.accounts.model.AccountEntity;
import com.lyaev.accounts.repository.AccountRepository;
import com.lyaev.accounts.repository.OperationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    OperationsRepository operationsRepository;

    public AccountEntity createOrUpdateAccount(AccountEntity account) {
        if (account == null) return null;
        AccountEntity accountTemp = accountRepository.findByName(account.getName());
        if (accountTemp != null) {
            accountTemp.setAmount(account.getAmount());
            accountTemp.setDateOpen(account.getDateOpen());
            return accountRepository.save(accountTemp);
        }
        return accountRepository.save(account);
    }

    private BigDecimal summDedit(String accountName) {
        BigDecimal amountDebit = operationsRepository.summDebit(accountName);
        return amountDebit == null ? new BigDecimal("0.0") : amountDebit;
    }

    private BigDecimal summCredit(String accountName) {
        BigDecimal amountCredit = operationsRepository.summCredit(accountName);
        return amountCredit == null ? new BigDecimal("0.0") : amountCredit;
    }

    public BigDecimal getSummAccount(String accountName) {
        return summDedit(accountName).subtract(summCredit(accountName));
    }

    public void updateAmount(String accountName) {
        if ((accountName == null) && (accountName.equals(""))) return;
        AccountEntity account = accountRepository.findByName(accountName);
        if (account != null) {
            account.setAmount(getSummAccount(accountName));
            accountRepository.save(account);
        }
    }

    public List<AccountEntity> getAllAccounts() {
        List<AccountEntity> accounts = accountRepository.findAll();
        if (accounts.size() > 0) {
            return accounts;
        } else {
            return new ArrayList<AccountEntity>();
        }
    }

    public void deleteAccount(String name) {
        if ((name != null) && (name != "")) return;
        AccountEntity account = accountRepository.findByName(name);
        if (account != null) {
            accountRepository.delete(account);
        }
    }
}
