package com.lyaev.accounts.service;

import com.lyaev.accounts.model.AccountEntity;
import com.lyaev.accounts.model.OperationJSON;
import com.lyaev.accounts.model.OperationsEntity;
import com.lyaev.accounts.repository.AccountRepository;
import com.lyaev.accounts.repository.OperationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OperationsService {
    @Autowired
    OperationsRepository operationsRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountService accountService;

    public OperationsEntity addOperation(OperationJSON operationJSON){
        if (operationJSON == null) return null;
        String accountName = operationJSON.getAccountName();
        OperationsEntity operation = new OperationsEntity();
        AccountEntity account = accountRepository.findByName(accountName);
        operation.setAccountEntity(account);
        BigDecimal sumOperation = operationJSON.getSumm();
        if (operationJSON.getIsDebit() == 1) operation.setSummDebit(sumOperation);
        else  operation.setSummCredit(sumOperation);
        OperationsEntity operationsEntity = operationsRepository.save(operation);
        return operationsEntity;
    }

    public List<OperationsEntity> getAllOperationsByAccountName(String accountName){
        List<OperationsEntity> operations = operationsRepository.findAllByAccountEntity_Name(accountName);
        if(operations.size() > 0) {
            return operations;
        } else {
            return new ArrayList<OperationsEntity>();
        }
    }

    public void deleteById(String accountName, Long id){
        Optional<OperationsEntity> operation = operationsRepository.findById(id);
        if (operation.isPresent() && operation.get().getAccountEntity().getName().equals(accountName)){
            operationsRepository.deleteById(id);
        }
    }
}
