package com.tuum.banking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuum.banking.dto.enums.CurrencyEnum;
import com.tuum.banking.dto.enums.DirectionEnum;
import com.tuum.banking.dto.request.TransactionRequest;
import com.tuum.banking.dto.response.TransactionResponse;
import com.tuum.banking.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {
    @MockBean
    private TransactionService transactionService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void should_getTransaction_successfully() throws Exception {

        final TransactionResponse response = TransactionResponse.builder()
                .transactionId(1L)
                .accountId(1L)
                .amount(BigDecimal.valueOf(10))
                .direction(DirectionEnum.IN)
                .currency(CurrencyEnum.EUR)
                .description("hi thats test description")
                .build();

        final List<TransactionResponse> transactionResponseList = new ArrayList<>();
        transactionResponseList.add(response);

        when(transactionService.getTransactionByAccountId(any())).thenReturn(transactionResponseList);

        mockMvc.perform(get("/transactions/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(transactionService).getTransactionByAccountId(anyLong());

    }

    @Test
    public void should_addTransaction_successfully() throws Exception {
        final TransactionRequest request = TransactionRequest.builder()
                .accountId(1L)
                .amount(BigDecimal.valueOf(10))
                .direction(DirectionEnum.IN.name())
                .currency(CurrencyEnum.EUR.name())
                .description("hi thats test description")
                .build();


        final TransactionResponse response = TransactionResponse.builder()
                .transactionId(1L)
                .accountId(1L)
                .amount(BigDecimal.valueOf(10))
                .direction(DirectionEnum.IN)
                .currency(CurrencyEnum.EUR)
                .description("hi thats test description")
                .build();

        String jsonStr = objectMapper.writeValueAsString(request);

        when(transactionService.addTransaction(any(TransactionRequest.class))).thenReturn(response);

        mockMvc.perform(post("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonStr))
                .andExpect(status().isOk());

        verify(transactionService).addTransaction(any(TransactionRequest.class));
    }

    @Test
    public void should_deleteTransaction_successfully() throws Exception {
        final TransactionResponse response = TransactionResponse.builder()
                .transactionId(1L)
                .build();

        when(transactionService.deleteTransaction(any())).thenReturn(response);

        mockMvc.perform(delete("/transactions/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(transactionService).deleteTransaction(anyLong());
    }
}
