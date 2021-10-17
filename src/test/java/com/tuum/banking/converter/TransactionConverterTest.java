package com.tuum.banking.converter;

import com.tuum.banking.dto.enums.CurrencyEnum;
import com.tuum.banking.dto.enums.DirectionEnum;
import com.tuum.banking.dto.request.TransactionRequest;
import com.tuum.banking.dto.response.TransactionResponse;
import com.tuum.banking.model.Balance;
import com.tuum.banking.model.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TransactionConverterTest {

    @InjectMocks
    private TransactionConverter converter;

    @Test
    public void shouldSuccessfullyConvertRequestToEntity() {
        final TransactionRequest request = TransactionRequest.builder()
                .id(1L)
                .accountId(1L)
                .amount(BigDecimal.TEN)
                .direction(DirectionEnum.IN.name())
                .currency(CurrencyEnum.EUR.name())
                .description("hi thats test description")
                .build();

        final Balance balance = Balance.builder()
                .id(1L)
                .accountId(1L)
                .availableAmount(BigDecimal.valueOf(100))
                .currency(CurrencyEnum.EUR)
                .build();

        final BigDecimal balanceAfterTransaction = BigDecimal.valueOf(110);

        final Transaction transaction = converter.requestToEntity(request, balance, balanceAfterTransaction);
        assertEquals(transaction.getId(), request.getId());
        assertEquals(transaction.getAccountId(), request.getAccountId());
        assertEquals(transaction.getBalanceAfterTransaction(), balanceAfterTransaction);
        assertEquals(transaction.getAmount(), request.getAmount());
        assertEquals(transaction.getDescription(), request.getDescription());
        assertEquals(transaction.getCurrency(), CurrencyEnum.of(request.getCurrency()));
        assertEquals(transaction.getDirection(), DirectionEnum.of(request.getDirection()));

    }

    @Test
    public void shouldSuccessfullyConvertEntityToResponse() {
        final BigDecimal balanceAfterTransaction = BigDecimal.valueOf(110);

        final Transaction transaction = Transaction.builder()
                .id(1L)
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
                .availableAmount(BigDecimal.valueOf(100))
                .currency(CurrencyEnum.EUR)
                .build();


        final TransactionResponse response = converter.entityToResponse(transaction);
        assertEquals(response.getAccountId(), transaction.getAccountId());
        assertEquals(response.getBalanceAfterTransaction(), balanceAfterTransaction);
        assertEquals(response.getAmount(), transaction.getAmount());
        assertEquals(response.getDescription(), transaction.getDescription());
        assertEquals(response.getCurrency(), transaction.getCurrency());
        assertEquals(response.getDirection(), transaction.getDirection());
    }
}
