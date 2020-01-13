package com.lyaev.accounts.controller;

import com.lyaev.accounts.model.OperationJSON;
import com.lyaev.accounts.model.OperationsEntity;
import com.lyaev.accounts.service.OperationsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/operations")
public class OperationsRestController {
    @Autowired
    OperationsService operationsService;

    private static final Logger logger = LoggerFactory.getLogger(OperationsRestController.class);

    private void updateAmount(String accountName){

    }

    /**
     * URL example http://localhost:8080//api/operations/accTest1
     * Get all account's operations.
     */
    @GetMapping("{accountName}")
    public List<OperationsEntity> getAllOperationsByAccount(@PathVariable String accountName){
        return operationsService.getAllOperationsByAccountName(accountName);
    }

    /**
     * URL example http://localhost:8080/api/operations/accTest1
     * request body (json)  {"accountName":"accTest1","summ":33.5,"isDebit":"1"}
     * if found account by name, then replace
     */
    @PutMapping("{accountName}")
    public OperationsEntity addOperation(
            @RequestBody OperationJSON operationJSON){
        return operationsService.addOperation(operationJSON);
    }

    /**
     * URL example http://localhost:8080/api/operations/accTest1/7
     * Delete operation by ID
     */
    @DeleteMapping("{accountName}/{id}")
    public void deleteOperationByID(
            @PathVariable String accountName,
            @PathVariable Long id){
        operationsService.deleteById(accountName, id);
    }
}
