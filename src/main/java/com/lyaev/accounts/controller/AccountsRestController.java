package com.lyaev.accounts.controller;

import com.lyaev.accounts.model.AccoountEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountsRestController {
    private static final Logger logger = LoggerFactory.getLogger(AccountsRestController.class);

    @GetMapping
    public List<AccoountEntity> getAllAccounts(){

    }

    @GetMapping("{account}")
    public  AccoountEntity getAccount(){

    }

    @PostMapping("/debit/{account}")
    public AccoountEntity debitOperation(){

    }

    @PostMapping("/credit/{account}")
    public AccoountEntity creditOperation(){

    }


}
