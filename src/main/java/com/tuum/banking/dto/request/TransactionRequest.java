package com.tuum.banking.dto.request;

import com.tuum.banking.dto.enums.CurrencyEnum;
import com.tuum.banking.dto.enums.DirectionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {
    private Long accountId;
    private Double amount;
    private CurrencyEnum currency;
    private DirectionEnum direction;
    private String description;
}
