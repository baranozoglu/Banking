package com.tuum.banking.model;

import com.tuum.banking.dto.enums.CurrencyEnum;
import com.tuum.banking.dto.enums.DirectionEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private Long id;
    private Long accountId;
    private BigDecimal amount;
    private DirectionEnum direction;
    private CurrencyEnum currency;
    private String description;
    private BigDecimal balanceAfterTransaction;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public Transaction(Long id) {
        this.id = id;
    }
}
