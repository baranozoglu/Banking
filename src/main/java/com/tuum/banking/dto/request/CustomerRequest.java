package com.tuum.banking.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    private Long id = null;
    private String name;
    private String surname;
    private Long phone;
    private String email;
}
