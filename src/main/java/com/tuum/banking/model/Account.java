package com.tuum.banking.model;

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
public class Account {
    private Long id;
    private Customer customer;
    private Long customerId;
    private String country;
    private List<Balance> balances;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public Account(Long id) {
        this.id = id;
    }
}
