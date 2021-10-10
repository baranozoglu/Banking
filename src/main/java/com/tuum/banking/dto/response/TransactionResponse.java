package com.tuum.banking.dto.response;

import com.tuum.banking.dto.enums.CurrencyEnum;
import com.tuum.banking.dto.enums.DirectionEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
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
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
