package com.lyaev.accounts.controller;

import com.lyaev.accounts.model.OperationJSON;
import com.lyaev.accounts.model.OperationsEntity;
import com.lyaev.accounts.service.AccountService;
import com.lyaev.accounts.service.OperationsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/operations")
public class OperationsRestController {
    @Autowired
    OperationsService operationsService;

    @Autowired
    AccountService accountService;

    private static final Logger logger = LoggerFactory.getLogger(OperationsRestController.class);

    /**
     * URL example GET http://localhost:8080//api/operations/accTest1
     * Get all account's operations.
     */
    @GetMapping("{accountName}")
    public List<OperationsEntity> getAllOperationsByAccount(@PathVariable String accountName) {
        logger.info("getting all operations by account name: " + accountName);
        return operationsService.getAllOperationsByAccountName(accountName);
    }

    /**
     * URL example PUT http://localhost:8080/api/operations
     * request body (json)  {"accountName":"accTest1","summ":500.5, "isDebit":1}
     * Create a new operation. Update account's amount.
     */
    @Transactional(isolation= Isolation.REPEATABLE_READ)
    @PutMapping
    public OperationsEntity addOperation(
            @RequestBody OperationJSON operationsJson) {
        logger.info("adding new operation");
        OperationsEntity operation = operationsService.addOperation(operationsJson);
        return operation;
    }

    /**
     * URL example DELETE http://localhost:8080/api/operations/accTest1/7
     * Delete operation by ID
     */
    @Transactional(isolation= Isolation.REPEATABLE_READ)
    @DeleteMapping("{accountName}/{id}")
    public void deleteOperationByID(
            @PathVariable String accountName,
            @PathVariable Long id) {
        logger.info("deleting operation");
        operationsService.deleteById(accountName, id);
    }
}
