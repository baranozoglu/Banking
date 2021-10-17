package com.tuum.banking.model;

import com.tuum.banking.dto.enums.CurrencyEnum;
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
public class Balance {
    private Long id;
    private Long accountId;
    private BigDecimal availableAmount;
    private CurrencyEnum currency;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public Balance(Long id) {
        this.id = id;
    }

    public Balance(Long accountId, BigDecimal availableAmount, CurrencyEnum currency) {
        this.accountId = accountId;
        this.availableAmount = availableAmount;
        this.currency = currency;
    }
}
