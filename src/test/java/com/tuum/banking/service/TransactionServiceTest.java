package com.tuum.banking.service;

import com.tuum.banking.converter.TransactionConverter;
import com.tuum.banking.dto.enums.CurrencyEnum;
import com.tuum.banking.dto.enums.DirectionEnum;
import com.tuum.banking.dto.request.TransactionRequest;
import com.tuum.banking.dto.response.TransactionResponse;
import com.tuum.banking.exception.*;
import com.tuum.banking.mapper.TransactionMapper;
import com.tuum.banking.model.Balance;
import com.tuum.banking.model.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {
    @Mock
    private TransactionMapper transactionMapper;
    @Mock
    private TransactionConverter transactionConverter;
    @Mock
    private AccountService accountService;
    @Mock
    private BalanceService balanceService;

    @Spy
    @InjectMocks
    private TransactionService transactionService;

    @Test
    public void should_getTransaction_Successfully() {
        final Long transactionId = 1L;
        final Long accountId = 1L;
        final BigDecimal balanceAfterTransaction = BigDecimal.valueOf(110);

        final Transaction entity = Transaction.builder()
                .id(1L)
                .accountId(1L)
                .amount(BigDecimal.TEN)
                .direction(DirectionEnum.IN)
                .currency(CurrencyEnum.EUR)
                .balanceAfterTransaction(balanceAfterTransaction)
                .description("hi thats test description")
                .build();

        final TransactionResponse response = TransactionResponse.builder()
                .transactionId(1L)
                .accountId(1L)
                .amount(BigDecimal.TEN)
                .direction(DirectionEnum.IN)
                .currency(CurrencyEnum.EUR)
                .description("hi thats test description")
                .balanceAfterTransaction(balanceAfterTransaction)
                .build();
        final List<Transaction> entityList = new ArrayList<>();
        entityList.add(entity);

        when(accountService.getAccount(anyLong())).thenReturn(null);
        when(transactionMapper.findByAccountId(accountId)).thenReturn(entityList);
        when(transactionConverter.entityToResponse(any(Transaction.class))).thenReturn(response);

        List<TransactionResponse> transactionResponse = transactionService.getTransactionByAccountId(transactionId);

        assertEquals(transactionResponse.get(0).getAccountId(), entityList.get(0).getAccountId());
        assertEquals(transactionResponse.get(0).getAmount(), entityList.get(0).getAmount());
        assertEquals(transactionResponse.get(0).getCurrency(), entityList.get(0).getCurrency());
        assertEquals(transactionResponse.get(0).getDescription(), entityList.get(0).getDescription());
        assertEquals(transactionResponse.get(0).getDirection(), entityList.get(0).getDirection());
        assertEquals(transactionResponse.get(0).getBalanceAfterTransaction(), entityList.get(0).getBalanceAfterTransaction());
    }

    @Test(expected = NotFoundException.class)
    public void should_getTransaction_ThrowNotFoundException() {
        final Long transactionId = 1L;
        final Long accountId = 1L;
        final BigDecimal balanceAfterTransaction = BigDecimal.valueOf(110);

        final Transaction entity = Transaction.builder()
                .id(1L)
                .accountId(1L)
                .amount(BigDecimal.TEN)
                .direction(DirectionEnum.IN)
                .currency(CurrencyEnum.EUR)
                .balanceAfterTransaction(balanceAfterTransaction)
                .description("hi thats test description")
                .build();

        final TransactionResponse response = TransactionResponse.builder()
                .transactionId(1L)
                .accountId(1L)
                .amount(BigDecimal.TEN)
                .direction(DirectionEnum.IN)
                .currency(CurrencyEnum.EUR)
                .description("hi thats test description")
                .balanceAfterTransaction(balanceAfterTransaction)
                .build();
        final List<Transaction> entityList = new ArrayList<>();
        entityList.add(entity);

        when(accountService.getAccount(anyLong())).thenReturn(null);
        when(transactionMapper.findByAccountId(accountId)).thenReturn(null);
        when(transactionConverter.entityToResponse(any(Transaction.class))).thenReturn(response);

        List<TransactionResponse> transactionResponse = transactionService.getTransactionByAccountId(transactionId);

        assertEquals(transactionResponse.get(0).getAccountId(), entityList.get(0).getAccountId());
        assertEquals(transactionResponse.get(0).getAmount(), entityList.get(0).getAmount());
        assertEquals(transactionResponse.get(0).getCurrency(), entityList.get(0).getCurrency());
        assertEquals(transactionResponse.get(0).getDescription(), entityList.get(0).getDescription());
        assertEquals(transactionResponse.get(0).getDirection(), entityList.get(0).getDirection());
        assertEquals(transactionResponse.get(0).getBalanceAfterTransaction(), entityList.get(0).getBalanceAfterTransaction());
    }

    @Test
    public void should_addTransaction_Successfully() {
        final BigDecimal balanceAfterTransaction = BigDecimal.valueOf(110);
        final Long accountId = 1L;

        final Transaction entity = Transaction.builder()
                .id(1L)
                .accountId(1L)
                .amount(BigDecimal.TEN)
                .direction(DirectionEnum.IN)
                .currency(CurrencyEnum.EUR)
                .balanceAfterTransaction(balanceAfterTransaction)
                .description("hi thats test description")
                .build();

        final TransactionRequest request = TransactionRequest.builder()
                .accountId(1L)
                .amount(BigDecimal.TEN)
                .direction(DirectionEnum.IN.name())
                .currency(CurrencyEnum.EUR.name())
                .description("hi thats test description")
                .build();

        final TransactionResponse response = TransactionResponse.builder()
                .transactionId(1L)
                .accountId(1L)
                .amount(BigDecimal.TEN)
                .direction(DirectionEnum.IN)
                .currency(CurrencyEnum.EUR)
                .balanceAfterTransaction(balanceAfterTransaction)
                .description("hi thats test description")
                .build();
        final Balance balance = Balance.builder()
                .id(1L)
                .accountId(1L)
                .availableAmount(BigDecimal.valueOf(150))
                .currency(CurrencyEnum.EUR)
                .build();

        final List<TransactionResponse> transactionResponseList = new ArrayList<>();
        transactionResponseList.add(response);
        final List<Balance> balanceList = new ArrayList<>();
        balanceList.add(balance);

        when(balanceService.getBalanceByAccountId(anyLong())).thenReturn(balanceList);
        when(balanceService.updateBalance(any(Balance.class))).thenReturn(balance);
        when(transactionConverter.requestToEntity(any(TransactionRequest.class), any(Balance.class), any())).thenReturn(entity);
        when(transactionMapper.addTransaction(any(Transaction.class))).thenReturn(1L);
        when(transactionMapper.findById(anyLong())).thenReturn(entity);
        when(transactionConverter.entityToResponse(any(Transaction.class))).thenReturn(response);

        TransactionResponse transactionResponse = transactionService.addTransaction(request);

        assertEquals(transactionResponse.getAccountId(), request.getAccountId());
        assertEquals(transactionResponse.getAmount(), request.getAmount());
        assertEquals(transactionResponse.getCurrency(), CurrencyEnum.of(request.getCurrency()));
        assertEquals(transactionResponse.getDescription(), request.getDescription());
        assertEquals(transactionResponse.getDirection(), DirectionEnum.of(request.getDirection()));
        assertEquals(transactionResponse.getBalanceAfterTransaction(), balanceAfterTransaction);
    }

    @Test(expected = NotFoundException.class)
    public void should_addTransaction_NotFoundException() {
        final BigDecimal balanceAfterTransaction = BigDecimal.valueOf(110);
        final Long accountId = 1L;

        final Transaction entity = Transaction.builder()
                .id(1L)
                .accountId(1L)
                .amount(BigDecimal.TEN)
                .direction(DirectionEnum.IN)
                .currency(CurrencyEnum.EUR)
                .balanceAfterTransaction(balanceAfterTransaction)
                .description("hi thats test description")
                .build();

        final TransactionRequest request = TransactionRequest.builder()
                .accountId(1L)
                .amount(BigDecimal.TEN)
                .direction(DirectionEnum.IN.name())
                .currency(CurrencyEnum.EUR.name())
                .description("hi thats test description")
                .build();

        final TransactionResponse response = TransactionResponse.builder()
                .transactionId(1L)
                .accountId(1L)
                .amount(BigDecimal.TEN)
                .direction(DirectionEnum.IN)
                .currency(CurrencyEnum.EUR)
                .balanceAfterTransaction(balanceAfterTransaction)
                .description("hi thats test description")
                .build();
        final Balance balance = Balance.builder()
                .id(1L)
                .accountId(1L)
                .availableAmount(BigDecimal.valueOf(150))
                .currency(CurrencyEnum.EUR)
                .build();

        final List<TransactionResponse> transactionResponseList = new ArrayList<>();
        transactionResponseList.add(response);
        final List<Balance> balanceList = new ArrayList<>();

        when(balanceService.getBalanceByAccountId(anyLong())).thenReturn(balanceList);
        when(balanceService.updateBalance(any(Balance.class))).thenReturn(balance);
        when(transactionConverter.requestToEntity(any(TransactionRequest.class), any(Balance.class), any())).thenReturn(entity);
        when(transactionMapper.addTransaction(any(Transaction.class))).thenReturn(1L);
        when(transactionMapper.findById(anyLong())).thenReturn(entity);
        when(transactionConverter.entityToResponse(any(Transaction.class))).thenReturn(response);

        TransactionResponse transactionResponse = transactionService.addTransaction(request);

        assertEquals(transactionResponse.getAccountId(), request.getAccountId());
        assertEquals(transactionResponse.getAmount(), request.getAmount());
        assertEquals(transactionResponse.getCurrency(), CurrencyEnum.of(request.getCurrency()));
        assertEquals(transactionResponse.getDescription(), request.getDescription());
        assertEquals(transactionResponse.getDirection(), DirectionEnum.of(request.getDirection()));
        assertEquals(transactionResponse.getBalanceAfterTransaction(), balanceAfterTransaction);
    }

    @Test(expected = InvalidCurrencyException.class)
    public void should_addTransaction_InvalidCurrencyException() {
        final BigDecimal balanceAfterTransaction = BigDecimal.valueOf(110);
        final Long accountId = 1L;

        final Transaction entity = Transaction.builder()
                .id(1L)
                .accountId(1L)
                .amount(BigDecimal.TEN)
                .direction(DirectionEnum.IN)
                .currency(CurrencyEnum.EUR)
                .balanceAfterTransaction(balanceAfterTransaction)
                .description("hi thats test description")
                .build();

        final TransactionRequest request = TransactionRequest.builder()
                .accountId(1L)
                .amount(BigDecimal.TEN)
                .direction(DirectionEnum.IN.name())
                .currency("TRY")
                .description("hi thats test description")
                .build();

        final TransactionResponse response = TransactionResponse.builder()
                .transactionId(1L)
                .accountId(1L)
                .amount(BigDecimal.TEN)
                .direction(DirectionEnum.IN)
                .currency(CurrencyEnum.EUR)
                .balanceAfterTransaction(balanceAfterTransaction)
                .description("hi thats test description")
                .build();
        final Balance balance = Balance.builder()
                .id(1L)
                .accountId(1L)
                .availableAmount(BigDecimal.valueOf(150))
                .currency(CurrencyEnum.EUR)
                .build();

        final List<TransactionResponse> transactionResponseList = new ArrayList<>();
        transactionResponseList.add(response);
        final List<Balance> balanceList = new ArrayList<>();
        balanceList.add(balance);

        when(balanceService.getBalanceByAccountId(anyLong())).thenReturn(balanceList);
        when(balanceService.updateBalance(any(Balance.class))).thenReturn(balance);
        when(transactionConverter.requestToEntity(any(TransactionRequest.class), any(Balance.class), any())).thenReturn(entity);
        when(transactionMapper.addTransaction(any(Transaction.class))).thenReturn(1L);
        when(transactionMapper.findById(anyLong())).thenReturn(entity);
        when(transactionConverter.entityToResponse(any(Transaction.class))).thenReturn(response);

        TransactionResponse transactionResponse = transactionService.addTransaction(request);

        assertEquals(transactionResponse.getAccountId(), request.getAccountId());
        assertEquals(transactionResponse.getAmount(), request.getAmount());
        assertEquals(transactionResponse.getCurrency(), CurrencyEnum.of(request.getCurrency()));
        assertEquals(transactionResponse.getDescription(), request.getDescription());
        assertEquals(transactionResponse.getDirection(), DirectionEnum.of(request.getDirection()));
        assertEquals(transactionResponse.getBalanceAfterTransaction(), balanceAfterTransaction);
    }

    @Test(expected = InvalidDirectionException.class)
    public void should_addTransaction_InvalidDirectionException() {
        final BigDecimal balanceAfterTransaction = BigDecimal.valueOf(110);

        final Transaction entity = Transaction.builder()
                .id(1L)
                .accountId(1L)
                .amount(BigDecimal.TEN)
                .direction(DirectionEnum.IN)
                .currency(CurrencyEnum.EUR)
                .balanceAfterTransaction(balanceAfterTransaction)
                .description("hi thats test description")
                .build();

        final TransactionRequest request = TransactionRequest.builder()
                .accountId(1L)
                .amount(BigDecimal.TEN)
                .direction("COME")
                .currency(CurrencyEnum.EUR.name())
                .description("hi thats test description")
                .build();

        final TransactionResponse response = TransactionResponse.builder()
                .transactionId(1L)
                .accountId(1L)
                .amount(BigDecimal.TEN)
                .direction(DirectionEnum.IN)
                .currency(CurrencyEnum.EUR)
                .balanceAfterTransaction(balanceAfterTransaction)
                .description("hi thats test description")
                .build();
        final Balance balance = Balance.builder()
                .id(1L)
                .accountId(1L)
                .availableAmount(BigDecimal.valueOf(150))
                .currency(CurrencyEnum.EUR)
                .build();

        final List<TransactionResponse> transactionResponseList = new ArrayList<>();
        transactionResponseList.add(response);
        final List<Balance> balanceList = new ArrayList<>();
        balanceList.add(balance);

        when(balanceService.getBalanceByAccountId(anyLong())).thenReturn(balanceList);
        when(balanceService.updateBalance(any(Balance.class))).thenReturn(balance);
        when(transactionConverter.requestToEntity(any(TransactionRequest.class), any(Balance.class), any())).thenReturn(entity);
        when(transactionMapper.addTransaction(any(Transaction.class))).thenReturn(1L);
        when(transactionMapper.findById(anyLong())).thenReturn(entity);
        when(transactionConverter.entityToResponse(any(Transaction.class))).thenReturn(response);

        TransactionResponse transactionResponse = transactionService.addTransaction(request);

        assertEquals(transactionResponse.getAccountId(), request.getAccountId());
        assertEquals(transactionResponse.getAmount(), request.getAmount());
        assertEquals(transactionResponse.getCurrency(), CurrencyEnum.of(request.getCurrency()));
        assertEquals(transactionResponse.getDescription(), request.getDescription());
        assertEquals(transactionResponse.getDirection(), DirectionEnum.of(request.getDirection()));
        assertEquals(transactionResponse.getBalanceAfterTransaction(), balanceAfterTransaction);
    }

    @Test(expected = InvalidAmountException.class)
    public void should_addTransaction_InvalidAmountException() {
        final BigDecimal balanceAfterTransaction = BigDecimal.valueOf(110);

        final Transaction entity = Transaction.builder()
                .id(1L)
                .accountId(1L)
                .amount(BigDecimal.TEN)
                .direction(DirectionEnum.IN)
                .currency(CurrencyEnum.EUR)
                .balanceAfterTransaction(balanceAfterTransaction)
                .description("hi thats test description")
                .build();

        final TransactionRequest request = TransactionRequest.builder()
                .accountId(1L)
                .amount(BigDecimal.valueOf(-10))
                .direction(DirectionEnum.IN.name())
                .currency(CurrencyEnum.EUR.name())
                .description("hi thats test description")
                .build();

        final TransactionResponse response = TransactionResponse.builder()
                .transactionId(1L)
                .accountId(1L)
                .amount(BigDecimal.TEN)
                .direction(DirectionEnum.IN)
                .currency(CurrencyEnum.EUR)
                .balanceAfterTransaction(balanceAfterTransaction)
                .description("hi thats test description")
                .build();
        final Balance balance = Balance.builder()
                .id(1L)
                .accountId(1L)
                .availableAmount(BigDecimal.valueOf(150))
                .currency(CurrencyEnum.EUR)
                .build();

        final List<TransactionResponse> transactionResponseList = new ArrayList<>();
        transactionResponseList.add(response);
        final List<Balance> balanceList = new ArrayList<>();
        balanceList.add(balance);

        when(balanceService.getBalanceByAccountId(anyLong())).thenReturn(balanceList);
        when(balanceService.updateBalance(any(Balance.class))).thenReturn(balance);
        when(transactionConverter.requestToEntity(any(TransactionRequest.class), any(Balance.class), any())).thenReturn(entity);
        when(transactionMapper.addTransaction(any(Transaction.class))).thenReturn(1L);
        when(transactionMapper.findById(anyLong())).thenReturn(entity);
        when(transactionConverter.entityToResponse(any(Transaction.class))).thenReturn(response);

        TransactionResponse transactionResponse = transactionService.addTransaction(request);

        assertEquals(transactionResponse.getAccountId(), request.getAccountId());
        assertEquals(transactionResponse.getAmount(), request.getAmount());
        assertEquals(transactionResponse.getCurrency(), CurrencyEnum.of(request.getCurrency()));
        assertEquals(transactionResponse.getDescription(), request.getDescription());
        assertEquals(transactionResponse.getDirection(), DirectionEnum.of(request.getDirection()));
        assertEquals(transactionResponse.getBalanceAfterTransaction(), balanceAfterTransaction);
    }

    @Test(expected = InsufficientFundsException.class)
    public void should_addTransaction_InsufficientFundsException() {
        final BigDecimal balanceAfterTransaction = BigDecimal.valueOf(110);

        final Transaction entity = Transaction.builder()
                .id(1L)
                .accountId(1L)
                .amount(BigDecimal.valueOf(210))
                .direction(DirectionEnum.IN)
                .currency(CurrencyEnum.EUR)
                .balanceAfterTransaction(balanceAfterTransaction)
                .description("hi thats test description")
                .build();

        final TransactionRequest request = TransactionRequest.builder()
                .accountId(1L)
                .amount(BigDecimal.valueOf(210))
                .direction(DirectionEnum.OUT.name())
                .currency(CurrencyEnum.EUR.name())
                .description("hi thats test description")
                .build();

        final TransactionResponse response = TransactionResponse.builder()
                .transactionId(1L)
                .accountId(1L)
                .amount(BigDecimal.valueOf(210))
                .direction(DirectionEnum.OUT)
                .currency(CurrencyEnum.EUR)
                .balanceAfterTransaction(balanceAfterTransaction)
                .description("hi thats test description")
                .build();
        final Balance balance = Balance.builder()
                .id(1L)
                .accountId(1L)
                .availableAmount(BigDecimal.valueOf(150))
                .currency(CurrencyEnum.EUR)
                .build();

        final List<TransactionResponse> transactionResponseList = new ArrayList<>();
        transactionResponseList.add(response);
        final List<Balance> balanceList = new ArrayList<>();
        balanceList.add(balance);

        when(balanceService.getBalanceByAccountId(anyLong())).thenReturn(balanceList);
        when(balanceService.updateBalance(any(Balance.class))).thenReturn(balance);
        when(transactionConverter.requestToEntity(any(TransactionRequest.class), any(Balance.class), any())).thenReturn(entity);
        when(transactionMapper.addTransaction(any(Transaction.class))).thenReturn(1L);
        when(transactionMapper.findById(anyLong())).thenReturn(entity);
        when(transactionConverter.entityToResponse(any(Transaction.class))).thenReturn(response);

        TransactionResponse transactionResponse = transactionService.addTransaction(request);

        assertEquals(transactionResponse.getAccountId(), request.getAccountId());
        assertEquals(transactionResponse.getAmount(), request.getAmount());
        assertEquals(transactionResponse.getCurrency(), CurrencyEnum.of(request.getCurrency()));
        assertEquals(transactionResponse.getDescription(), request.getDescription());
        assertEquals(transactionResponse.getDirection(), DirectionEnum.of(request.getDirection()));
        assertEquals(transactionResponse.getBalanceAfterTransaction(), balanceAfterTransaction);
    }

    @Test
    public void should_deleteTransaction_Successfully() {
        final Long transactionId = 1L;

        when(transactionMapper.deleteTransaction(anyLong())).thenReturn(transactionId);
        TransactionResponse transactionResponse = transactionService.deleteTransaction(transactionId);

        assertEquals(transactionResponse.getTransactionId(), transactionId);

    }
}
