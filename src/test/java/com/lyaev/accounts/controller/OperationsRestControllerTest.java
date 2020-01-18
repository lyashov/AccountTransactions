package com.lyaev.accounts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyaev.accounts.model.AccountEntity;
import com.lyaev.accounts.model.OperationJSON;
import com.lyaev.accounts.model.OperationsEntity;
import com.lyaev.accounts.service.AccountService;
import com.lyaev.accounts.service.OperationsService;
import org.junit.Before;
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
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = OperationsRestController.class)
class OperationsRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    OperationsService operationsService;

    @MockBean
    AccountService accountService;

    AccountEntity account = new AccountEntity();
    OperationJSON operationJSON = new OperationJSON();
    OperationsEntity operation = new OperationsEntity();

    @BeforeEach
    public void BeforeTests(){
        final String ACCOUNT_NAME = "testAccount";
        final byte IS_DEBIT = 1;
        BigDecimal initSum = new BigDecimal("0.0");

        account.setName(ACCOUNT_NAME);
        account.setAmount(initSum);

        operationJSON.setAccountName(ACCOUNT_NAME);
        operationJSON.setIsDebit(IS_DEBIT);
        operationJSON.setSumm(initSum);

        operation.setAccountEntity(account);
        operation.setSummCredit(initSum);
    }

    @Test
    void getAllOperationsByAccount() throws Exception {
        List<OperationsEntity> operations = new ArrayList<>();
        operations.add(operation);

        when(operationsService.getAllOperationsByAccountName("testAccount")).thenReturn(operations);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonParameter = objectMapper.writeValueAsString(operations);

        this.mockMvc.perform(
                get("/api/operations/testAccount")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonParameter))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(jsonParameter)));
    }

    @Test
    void addOperation() throws Exception {
        when(operationsService.addOperation(operationJSON)).thenReturn(operation);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(operation);
        String jsonResponse = objectMapper.writeValueAsString(operation);

        this.mockMvc.perform(
                put("/api/operations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(jsonResponse)));
    }
}