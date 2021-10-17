package com.tuum.banking.service;

import com.tuum.banking.converter.AccountConverter;
import com.tuum.banking.dto.enums.CurrencyEnum;
import com.tuum.banking.dto.request.AccountRequest;
import com.tuum.banking.dto.response.AccountResponse;
import com.tuum.banking.exception.InvalidAccountException;
import com.tuum.banking.mapper.AccountMapper;
import com.tuum.banking.model.Account;
import com.tuum.banking.model.Balance;
import com.tuum.banking.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {
    @Mock
    private AccountMapper accountMapper;
    @Mock
    private AccountConverter accountConverter;
    @Mock
    private BalanceService balanceService;
    @InjectMocks
    private AccountService accountService;

    @Test
    public void should_getAccount_Successfully() {
        final Long accountId = 1L;
        List<CurrencyEnum> currencyEnumList = new ArrayList<>();
        currencyEnumList.add(CurrencyEnum.EUR);
        currencyEnumList.add(CurrencyEnum.GBP);

        final List<Balance> balanceList = new ArrayList<>();
        currencyEnumList.forEach(currencyEnum -> {
            balanceList.add(new Balance(1L, BigDecimal.ZERO, currencyEnum));
        });

        final Account entity = Account.builder()
                .id(1L)
                .country("ankara")
                .customer(new Customer(1L))
                .customerId(1L)
                .balances(balanceList)
                .build();
        final AccountResponse response = AccountResponse.builder()
                .accountId(1L)
                .customer(new Customer(1L))
                .customerId(1L)
                .balanceList(balanceList)
                .build();

        when(accountMapper.findById(accountId)).thenReturn(entity);
        when(accountConverter.entityToResponse(any(Account.class))).thenReturn(response);

        AccountResponse accountResponse = accountService.getAccount(accountId);

        assertEquals(accountResponse.getAccountId(), response.getAccountId());
        assertEquals(accountResponse.getCustomer(), response.getCustomer());
        assertEquals(accountResponse.getBalanceList(), response.getBalanceList());
        assertEquals(accountResponse.getCustomerId(), response.getCustomerId());
    }

    @Test(expected = InvalidAccountException.class)
    public void should_getAccount_ThrowNotFoundException() {
        final Long accountId = 1L;
        List<CurrencyEnum> currencyEnumList = new ArrayList<>();
        currencyEnumList.add(CurrencyEnum.EUR);
        currencyEnumList.add(CurrencyEnum.GBP);

        final List<Balance> balanceList = new ArrayList<>();
        currencyEnumList.forEach(currencyEnum -> {
            balanceList.add(new Balance(1L, BigDecimal.ZERO, currencyEnum));
        });

        final AccountResponse response = AccountResponse.builder()
                .accountId(1L)
                .customer(new Customer(1L))
                .customerId(1L)
                .balanceList(balanceList)
                .build();

        when(accountMapper.findById(accountId)).thenReturn(null);
        when(accountConverter.entityToResponse(any(Account.class))).thenReturn(response);

        AccountResponse accountResponse = accountService.getAccount(accountId);

        assertEquals(accountResponse.getAccountId(), response.getAccountId());
        assertEquals(accountResponse.getCustomer(), response.getCustomer());
        assertEquals(accountResponse.getBalanceList(), response.getBalanceList());
        assertEquals(accountResponse.getCustomerId(), response.getCustomerId());
    }

    @Test
    public void should_addAccount_Successfully() {
        final Long accountId = 1L;
        List<CurrencyEnum> currencyEnumList = new ArrayList<>();
        currencyEnumList.add(CurrencyEnum.EUR);
        currencyEnumList.add(CurrencyEnum.GBP);

        final List<Balance> balanceList = new ArrayList<>();
        currencyEnumList.forEach(currencyEnum -> {
            balanceList.add(new Balance(1L, BigDecimal.ZERO, currencyEnum));
        });

        final Account entity = Account.builder()
                .id(1L)
                .country("ankara")
                .customer(new Customer(1L))
                .customerId(1L)
                .balances(balanceList)
                .build();
        final AccountResponse response = AccountResponse.builder()
                .accountId(1L)
                .customer(new Customer(1L))
                .customerId(1L)
                .balanceList(balanceList)
                .build();
        final AccountRequest request = AccountRequest.builder()
                .id(1L)
                .country("ankara")
                .customerId(1L)
                .currencyList(currencyEnumList)
                .build();

        when(accountConverter.requestToEntity(any(AccountRequest.class), anyList())).thenReturn(entity);
        when(accountMapper.addAccount(any(Account.class))).thenReturn(1L);
        when(accountMapper.findById(anyLong())).thenReturn(entity);
        when(accountConverter.entityToResponse(any(Account.class))).thenReturn(response);
        when(balanceService.createBalances(anyList(), anyLong())).thenReturn(balanceList);

        AccountResponse accountResponse = accountService.addAccount(request);

        assertEquals(accountResponse.getBalanceList(), balanceList);
        assertEquals(accountResponse.getCustomerId(), request.getCustomerId());
        assertEquals(currencyEnumList, request.getCurrencyList());
    }

    @Test
    public void should_updateAccount_Successfully() {
        final Long accountId = 0L;
        List<CurrencyEnum> currencyEnumList = new ArrayList<>();
        currencyEnumList.add(CurrencyEnum.EUR);
        currencyEnumList.add(CurrencyEnum.GBP);

        final List<Balance> balanceList = new ArrayList<>();
        currencyEnumList.forEach(currencyEnum -> {
            balanceList.add(new Balance(1L, BigDecimal.ZERO, currencyEnum));
        });

        final Account entity = Account.builder()
                .id(1L)
                .country("ankara")
                .customer(new Customer(1L))
                .customerId(1L)
                .balances(balanceList)
                .build();
        final AccountResponse response = AccountResponse.builder()
                .accountId(1L)
                .customer(new Customer(1L))
                .customerId(1L)
                .balanceList(balanceList)
                .build();
        final AccountRequest request = AccountRequest.builder()
                .id(1L)
                .country("ankara")
                .customerId(1L)
                .currencyList(currencyEnumList)
                .build();
        when(accountConverter.requestToEntity(any(AccountRequest.class), anyList())).thenReturn(entity);
        when(accountMapper.updateAccount(any(Account.class))).thenReturn(accountId);

        AccountResponse accountResponse = accountService.updateAccount(request);

        assertEquals(accountResponse.getAccountId(), accountId);
    }

    @Test
    public void should_deleteAccount_Successfully() {
        final Long accountId = 1L;

        when(accountMapper.deleteAccount(anyLong())).thenReturn(accountId);
        AccountResponse accountResponse = accountService.deleteAccount(accountId);

        assertEquals(accountResponse.getAccountId(), accountId);

    }
}
