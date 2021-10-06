package com.tuum.banking.model;

import com.tuum.banking.dto.enums.CurrencyEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Balance {
    private Long id;
    private Long accountId;
    private Double availableAmount;
    private CurrencyEnum currency;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public Balance(Long id) {
        this.id = id;
    }

    public Balance(Long accountId, Double availableAmount, CurrencyEnum currency) {
        this.accountId = accountId;
        this.availableAmount = availableAmount;
        this.currency = currency;
    }
}
