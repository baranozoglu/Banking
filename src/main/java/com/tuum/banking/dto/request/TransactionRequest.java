package com.tuum.banking.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {
    private Long id;
    private Long accountId;
    private BigDecimal amount;
    private String currency;
    private String direction;
    private String description;
}
