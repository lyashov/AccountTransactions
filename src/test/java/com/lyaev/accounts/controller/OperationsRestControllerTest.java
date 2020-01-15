package com.lyaev.accounts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyaev.accounts.model.AccountEntity;
import com.lyaev.accounts.model.OperationJSON;
import com.lyaev.accounts.model.OperationsEntity;
import com.lyaev.accounts.service.AccountService;
import com.lyaev.accounts.service.OperationsService;
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

    private AccountEntity createTestAccount(){
        AccountEntity account = new AccountEntity();
        account.setName("testAccount");
        account.setAmount(new BigDecimal("0.0"));
        return account;
    }

    private OperationJSON createTestOperationJSON(){
        OperationJSON operation = new OperationJSON();
        operation.setAccountName("testAccount");
        operation.setIsDebit((byte) 1);
        operation.setSumm(new BigDecimal("100.0"));
        return operation;
    }

    private OperationsEntity createTestOperation(){
        OperationsEntity operation = new OperationsEntity();
        operation.setAccountEntity(createTestAccount());
        operation.setSummCredit(new BigDecimal("100"));
        return operation;
    }

    @Test
    void getAllOperationsByAccount() throws Exception {
        List<OperationsEntity> operations = new ArrayList<>();
        operations.add(createTestOperation());

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
        OperationJSON operation = createTestOperationJSON();
        OperationsEntity operationsEntity = createTestOperation();

        when(operationsService.addOperation(operation)).thenReturn(operationsEntity);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(operation);
        String jsonResponse = objectMapper.writeValueAsString(operationsEntity);

        this.mockMvc.perform(
                put("/api/operations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(jsonResponse)));
    }
}