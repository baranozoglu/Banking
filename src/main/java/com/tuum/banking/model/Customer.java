package com.tuum.banking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private Long id;
    private String name;
    private String surname;
    private Long phone;
    private String email;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public Customer(Long id) {
        this.id = id;
    }
}
