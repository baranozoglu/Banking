package com.tuum.banking.converter;

import com.tuum.banking.dto.enums.CurrencyEnum;
import com.tuum.banking.dto.request.AccountRequest;
import com.tuum.banking.dto.response.AccountResponse;
import com.tuum.banking.model.Account;
import com.tuum.banking.model.Balance;
import com.tuum.banking.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AccountConverterTest {

    @InjectMocks
    private AccountConverter converter;

    @Test
    public void shouldSuccessfullyConvertRequestToEntity() {
        List<CurrencyEnum> currencyEnumList = new ArrayList<>();
        currencyEnumList.add(CurrencyEnum.EUR);
        currencyEnumList.add(CurrencyEnum.GBP);

        final List<Balance> balanceList = new ArrayList<>();
        currencyEnumList.forEach(currencyEnum -> {
            balanceList.add(new Balance(1L, BigDecimal.ZERO, currencyEnum));
        });

        final AccountRequest request = AccountRequest.builder()
                .id(1L)
                .country("ankara")
                .customerId(1L)
                .currencyList(currencyEnumList)
                .build();

        final Account account = converter.requestToEntity(request, balanceList);
        assertEquals(account.getId(), request.getId());
        assertEquals(account.getBalances(), balanceList);
        assertEquals(account.getCountry(), request.getCountry());
        assertEquals(account.getCustomerId(), request.getCustomerId());
    }

    @Test
    public void shouldSuccessfullyConvertEntityToResponse() {
        List<CurrencyEnum> currencyEnumList = new ArrayList<>();
        currencyEnumList.add(CurrencyEnum.EUR);
        currencyEnumList.add(CurrencyEnum.GBP);

        final List<Balance> balanceList = new ArrayList<>();
        currencyEnumList.forEach(currencyEnum -> {
            balanceList.add(new Balance(1L, BigDecimal.ZERO, currencyEnum));
        });

        final Account account = Account.builder()
                .id(1L)
                .country("ankara")
                .customer(new Customer(1L))
                .customerId(1L)
                .balances(balanceList)
                .build();

        final AccountResponse response = converter.entityToResponse(account);
        assertEquals(account.getBalances(), balanceList);
        assertEquals(account.getId(), response.getAccountId());
        assertEquals(account.getCustomerId(), response.getCustomerId());
        assertEquals(account.getCustomer(), response.getCustomer());
    }
}
