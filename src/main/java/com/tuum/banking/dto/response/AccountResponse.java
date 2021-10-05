package com.tuum.banking.dto.response;

import com.tuum.banking.model.Balance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {
    private Long accountId;
    private Long customerId;
    private List<Balance> balanceList;
}
