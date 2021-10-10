package com.tuum.banking.converter;

import com.tuum.banking.dto.enums.CurrencyEnum;
import com.tuum.banking.dto.enums.DirectionEnum;
import com.tuum.banking.dto.request.TransactionRequest;
import com.tuum.banking.dto.response.TransactionResponse;
import com.tuum.banking.model.Balance;
import com.tuum.banking.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter {

    public TransactionResponse entityToResponse(Transaction entity) {
        return TransactionResponse.builder()
                .transactionId(entity.getId())
                .accountId(entity.getAccountId())
                .amount(entity.getAmount())
                .currency(CurrencyEnum.of(entity.getCurrency().name()))
                .balanceAfterTransaction(entity.getBalanceAfterTransaction())
                .description(entity.getDescription())
                .direction(DirectionEnum.of(entity.getDirection().name()))
                .updatedAt(entity.getUpdatedAt())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public Transaction requestToEntity(TransactionRequest request, Balance balance, Double balanceAfterTransaction) {
        return Transaction.builder()
                .id(request.getId())
                .accountId(request.getAccountId())
                .amount(request.getAmount())
                .currency(CurrencyEnum.of(balance.getCurrency().name()))
                .description(request.getDescription())
                .direction(DirectionEnum.of(request.getDirection()))
                .balanceAfterTransaction(balanceAfterTransaction)
                .build();
    }

}
