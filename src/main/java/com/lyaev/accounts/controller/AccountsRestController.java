package com.lyaev.accounts.controller;

import com.lyaev.accounts.model.AccountEntity;
import com.lyaev.accounts.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return accountService.getAllAccounts();
    }

    /**
     * URL examplehttp://localhost:8080/api/accounts/accTest1
     * request body (json) {"name":"accTest1", "dateOpen":"2019-01-01T23:28:56.782Z"}
     * if found account by name, then replace it
     */
    @PutMapping
    public AccountEntity saveOrCreateAccount(
            @RequestBody AccountEntity account){
        return accountService.saveOrCreateAccount(account);
    }

    /**
     * http://localhost:8080/api/accounts/accTest1
     * Delete account by name
     */
    @DeleteMapping("{name}")
    public void deleteAccount(
            @PathVariable String name){
        accountService.deleteAccount(name);
    }
}
