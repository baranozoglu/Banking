package com.tuum.banking.model;

import com.tuum.banking.dto.enums.CurrencyEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Date createdAt;
}
