package com.tuum.banking.converter;

import com.tuum.banking.dto.request.AccountRequest;
import com.tuum.banking.dto.response.AccountResponse;
import com.tuum.banking.model.Account;
import com.tuum.banking.model.Balance;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountConverter {

    public AccountResponse entityToResponse(Account entity) {
        return AccountResponse.builder()
                .accountId(entity.getId())
                .customer(entity.getCustomer())
                .customerId(entity.getCustomer().getId())
                .balanceList(entity.getBalances())
                .updatedAt(entity.getUpdatedAt())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public Account requestToEntity(AccountRequest request, List<Balance> balances) {
        return Account.builder()
                .id(request.getId())
                .customerId(request.getCustomerId())
                .country(request.getCountry())
                .balances(balances)
                .build();
    }

}
