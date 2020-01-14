package com.lyaev.accounts.service;

import com.lyaev.accounts.model.AccountEntity;
import com.lyaev.accounts.model.OperationsEntity;
import com.lyaev.accounts.repository.OperationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OperationsService {
    @Autowired
    OperationsRepository operationsRepository;

    @Autowired
    AccountService accountService;

    public OperationsEntity addOperation(OperationsEntity operationFromJSON){
        if (operationFromJSON == null) return null;
        if (operationFromJSON.getAccountEntity() == null) return null;
        String accountName = operationFromJSON.getAccountEntity().getName();
        AccountEntity account = accountService.findByName(accountName);
        OperationsEntity operation = new OperationsEntity();
        operation.setAccountEntity(account);
        operation.setSummCredit(operationFromJSON.getSummDebit());
        operation.setSummCredit(operationFromJSON.getSummDebit());
        OperationsEntity operationsSaved = operationsRepository.save(operation);
        return operationsSaved;
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
