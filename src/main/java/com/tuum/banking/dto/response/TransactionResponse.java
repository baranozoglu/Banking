package com.tuum.banking.dto.response;

import com.tuum.banking.dto.enums.CurrencyEnum;
import com.tuum.banking.dto.enums.DirectionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {
    private Long accountId;
    private Long transactionId;
    private Double amount;
    private CurrencyEnum currency;
    private DirectionEnum direction;
    private String description;
    private Double balanceAfterTransaction;
}
