package com.lyaev.accounts.service;

import com.lyaev.accounts.model.AccountEntity;
import com.lyaev.accounts.model.OperationJSON;
import com.lyaev.accounts.model.OperationsEntity;
import com.lyaev.accounts.repository.AccountRepository;
import com.lyaev.accounts.repository.OperationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OperationsService {
    @Autowired
    OperationsRepository operationsRepository;

    @Autowired
    AccountService accountService;

    public Long moneyToCoins(Double money){
        Double coins = money * 100;
        return coins.longValue();
    }

    @Transactional
    public OperationsEntity addOperation(OperationJSON operationJSON){
        if (operationJSON == null) return null;
        OperationsEntity operation = new OperationsEntity();
        AccountEntity account = accountService.findByName(operationJSON.getAccountName());
        operation.setAccountEntity(account);
        Long sumOperation = moneyToCoins(operationJSON.getSumm());
        if (operationJSON.getIsDebit() == 1) operation.setSummDebit(sumOperation);
         else  operation.setSummCredit(sumOperation);
        return operationsRepository.save(operation);
    }

   /* public AccountEntity findByName(String name){
        return accountRepository.findByName(name);
    }*/

    public List<OperationsEntity> getAllOperationsByAccountName(String accountName){
        List<OperationsEntity> operations = operationsRepository.findAllByAccountEntity_Name(accountName);
        if(operations.size() > 0) {
            return operations;
        } else {
            return new ArrayList<OperationsEntity>();
        }
    }

 /*   @Transactional
    public void deleteAccount(String name){
        if ((name != null)&&(name != "")) {
            AccountEntity account = findByName(name);
            accountRepository.delete(account);
        }
    }*/
}
