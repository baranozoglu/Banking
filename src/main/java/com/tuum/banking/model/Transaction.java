package com.tuum.banking.model;

import com.tuum.banking.dto.enums.DirectionEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private Long id;
    private Long accountId;
    private Double amount;
    private DirectionEnum direction;
    private String description;
    private Double balanceAfterTransaction;
    private Date createdAt;
}
