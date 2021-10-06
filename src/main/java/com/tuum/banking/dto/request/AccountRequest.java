package com.tuum.banking.dto.request;

import com.tuum.banking.dto.enums.CurrencyEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {
    private Long id;
    private Long customerId;
    private String country;
    private List<CurrencyEnum> currencyList;
}
