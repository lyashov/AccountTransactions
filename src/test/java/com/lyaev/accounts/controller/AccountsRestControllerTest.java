package com.lyaev.accounts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyaev.accounts.model.AccountEntity;
import com.lyaev.accounts.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AccountsRestController.class)
class AccountsRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AccountService accountService;

    AccountEntity account = new AccountEntity();
    @BeforeEach
     public void BeforeTests(){
        final String ACCOUNT_NAME = "testAccount";
        BigDecimal initSum = new BigDecimal("0.0");

        account.setName(ACCOUNT_NAME);
        account.setAmount(initSum);
        account.setDateOpen(new Date());
    }

    @Test
    public void getAllAccounts() throws Exception {
        List<AccountEntity> accounts = new ArrayList<>();
        accounts.add(account);

        when(accountService.getAllAccounts()).thenReturn(accounts);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonParameter = objectMapper.writeValueAsString(accounts);

        this.mockMvc.perform(
                get("/api/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonParameter))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(jsonParameter)));
    }

    @Test
    void createOrUpdateAccount() throws Exception {
        when(accountService.createOrUpdateAccount(account)).thenReturn(account);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonParameter = objectMapper.writeValueAsString(account);

        this.mockMvc.perform(
                put("/api/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonParameter))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(jsonParameter)));
    }

    @Test
    void deleteAccount() throws Exception  {
        this.mockMvc.perform(
                delete("/api/accounts/testAccount")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andDo(print())
                .andExpect(status().isOk());
    }
}