package com.lyaev.accounts.controller;

import com.lyaev.accounts.model.AccoountEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountsRestController {
    private static final Logger logger = LoggerFactory.getLogger(AccountsRestController.class);

    @GetMapping
    public List<AccoountEntity> getAllAccounts(){
        return null;
    }

    @GetMapping("{account}")
    public  AccoountEntity getAccount(@PathVariable String account, Model model){
        return null;
    }

    @PostMapping("/debit/{account}")
    public AccoountEntity debitOperation(@PathVariable String account, Model model){
        return null;
    }

    @PostMapping("/credit/{account}")
    public AccoountEntity creditOperation(){
        return null;
    }
}
