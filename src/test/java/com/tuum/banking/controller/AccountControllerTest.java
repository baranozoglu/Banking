package com.tuum.banking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuum.banking.dto.enums.CurrencyEnum;
import com.tuum.banking.dto.request.AccountRequest;
import com.tuum.banking.dto.response.AccountResponse;
import com.tuum.banking.model.Balance;
import com.tuum.banking.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {
    @MockBean
    private AccountService accountService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void should_getAccount_successfully() throws Exception {
        List<CurrencyEnum> currencyEnumList = new ArrayList<>();
        currencyEnumList.add(CurrencyEnum.EUR);
        currencyEnumList.add(CurrencyEnum.GBP);

        final List<Balance> balanceList = new ArrayList<>();
        currencyEnumList.forEach(currencyEnum -> {
            balanceList.add(new Balance(1L, Double.valueOf(0), currencyEnum));
        });

        final AccountResponse response = AccountResponse.builder()
                .accountId(1L)
                .customerId(1L)
                .balanceList(balanceList)
                .build();

        when(accountService.getAccount(any())).thenReturn(response);

        mockMvc.perform(get("/accounts/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(accountService).getAccount(anyLong());

    }

    @Test
    public void should_addAccount_successfully() throws Exception {
        List<CurrencyEnum> currencyEnumList = new ArrayList<>();
        currencyEnumList.add(CurrencyEnum.EUR);
        currencyEnumList.add(CurrencyEnum.GBP);

        final List<Balance> balanceList = new ArrayList<>();
        currencyEnumList.forEach(currencyEnum -> {
            balanceList.add(new Balance(1L, Double.valueOf(0), currencyEnum));
        });

        final AccountRequest request = AccountRequest.builder()
                .id(1L)
                .country("ankara")
                .customerId(1L)
                .currencyList(currencyEnumList)
                .build();

        final AccountResponse response = AccountResponse.builder()
                .accountId(1L)
                .customerId(1L)
                .balanceList(balanceList)
                .build();

        String jsonStr = objectMapper.writeValueAsString(request);

        when(accountService.addAccount(any(AccountRequest.class))).thenReturn(response);

        mockMvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonStr))
                .andExpect(status().isOk());

        verify(accountService).addAccount(any(AccountRequest.class));
    }

    @Test
    public void should_updateAccount_successfully() throws Exception {
        List<CurrencyEnum> currencyEnumList = new ArrayList<>();
        currencyEnumList.add(CurrencyEnum.EUR);
        currencyEnumList.add(CurrencyEnum.GBP);

        final List<Balance> balanceList = new ArrayList<>();
        currencyEnumList.forEach(currencyEnum -> {
            balanceList.add(new Balance(1L, Double.valueOf(0), currencyEnum));
        });

        final AccountRequest request = AccountRequest.builder()
                .id(1L)
                .country("ankara")
                .customerId(1L)
                .currencyList(currencyEnumList)
                .build();

        final AccountResponse response = AccountResponse.builder()
                .accountId(1L)
                .customerId(1L)
                .balanceList(balanceList)
                .build();

        String jsonStr = objectMapper.writeValueAsString(request);

        when(accountService.updateAccount(any(AccountRequest.class))).thenReturn(response);

        mockMvc.perform(put("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonStr))
                .andExpect(status().isOk());

        verify(accountService).updateAccount(any(AccountRequest.class));
    }

    @Test
    public void should_deleteAccount_successfully() throws Exception {
        final AccountResponse response = AccountResponse.builder()
                .accountId(1L)
                .build();

        when(accountService.deleteAccount(any())).thenReturn(response);

        mockMvc.perform(delete("/accounts/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(accountService).deleteAccount(anyLong());
    }
}
