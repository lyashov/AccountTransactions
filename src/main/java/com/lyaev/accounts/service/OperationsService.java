package com.lyaev.accounts.service;

import com.lyaev.accounts.model.AccountEntity;
import com.lyaev.accounts.model.OperationJSON;
import com.lyaev.accounts.model.OperationsEntity;
import com.lyaev.accounts.repository.AccountRepository;
import com.lyaev.accounts.repository.OperationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    public BigDecimal getPermissibleSumOperation(String accountName, BigDecimal sumOperation) {
        BigDecimal amountSum = accountService.getSummAccount(accountName);
        return (sumOperation.min(amountSum));
    }

    public OperationsEntity addOperation(OperationJSON operationJSON) {
        if (operationJSON == null) return null;
        String accountName = operationJSON.getAccountName();
        OperationsEntity operation = new OperationsEntity();
        AccountEntity account = accountRepository.findByName(accountName);
        operation.setAccountEntity(account);
        Date dateOperation = null;
        try {
            dateOperation = new SimpleDateFormat("dd/MM/yyyy").parse(operationJSON.getDateOperation());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        operation.setDateOperation(dateOperation);
        BigDecimal sumOperation = operationJSON.getSumm();
        if (operationJSON.getIsDebit() == 1)
            operation.setSummDebit(sumOperation);
        else {
            BigDecimal permSumm = getPermissibleSumOperation(accountName, sumOperation);
            operation.setSummCredit(permSumm);
        }
        OperationsEntity operationsEntity = operationsRepository.save(operation);
        accountService.updateAmount(accountName);
        return operationsEntity;
    }

    public List<OperationsEntity> getAllOperationsByAccountName(String accountName) {
        List<OperationsEntity> operations = operationsRepository.findAllByAccountEntity_Name(accountName);
        if (operations.size() > 0) {
            return operations;
        } else {
            return new ArrayList<OperationsEntity>();
        }
    }

    public void deleteById(String accountName, Long id) {
        Optional<OperationsEntity> operation = operationsRepository.findById(id);
        if (operation.isPresent() && operation.get().getAccountEntity().getName().equals(accountName)) {
            operationsRepository.deleteById(id);
        }
        accountService.updateAmount(accountName);
    }
}
