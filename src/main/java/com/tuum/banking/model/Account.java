package com.tuum.banking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private Long id;
    private Long customerId;
    private String country;
    private List<Balance> balances;
    private Date createdAt;
}
