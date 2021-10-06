package com.tuum.banking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
    private Long id;
    private String name;
    private String surname;
    private Long phone;
    private String email;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
