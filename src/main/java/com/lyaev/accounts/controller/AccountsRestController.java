package com.lyaev.accounts.controller;

import com.lyaev.accounts.model.AccountEntity;
import com.lyaev.accounts.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountsRestController {
    @Autowired
    AccountService accountService;

    private static final Logger logger = LoggerFactory.getLogger(AccountsRestController.class);

    /**
     * URL example http://localhost:8080/api/accounts
     * Get all accounts.
     */
    @GetMapping
    public List<AccountEntity> getAllAccounts(){
        logger.info("getting all accounts");
        return accountService.getAllAccounts();
    }

    /**
     * URL example http://localhost:8080/api/accounts
     * request body (json) {"name":"accTest1", "amount":"0.0", "dateOpen":"2020-01-01T23:28:56.782Z"}
     * if found account by name, then replace it
     */
    @Transactional
    @PutMapping
    public AccountEntity createOrUpdateAccount(
            @RequestBody AccountEntity account){
        logger.info("creating or updating account");
        return accountService.createOrUpdateAccount(account);
    }

    /**
     * http://localhost:8080/api/accounts/accTest1
     * Delete account by name
     */
    @Transactional
    @DeleteMapping("{name}")
    public void deleteAccount(
            @PathVariable String name){
        logger.info("deleting account");
        accountService.deleteAccount(name);
    }
}
