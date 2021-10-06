package com.tuum.banking.dto.response;

import com.tuum.banking.model.Balance;
import com.tuum.banking.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {
    private Long accountId;
    private Long customerId;
    private Customer customer;
    private List<Balance> balanceList;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
