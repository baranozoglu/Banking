package com.tuum.banking.converter;

import com.tuum.banking.dto.request.CustomerRequest;
import com.tuum.banking.dto.response.CustomerResponse;
import com.tuum.banking.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter {

    public CustomerResponse entityToResponse(Customer entity) {
        return CustomerResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .updatedAt(entity.getUpdatedAt())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public Customer requestToEntity(CustomerRequest request) {
        return Customer.builder()
                .id(request.getId())
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .phone(request.getPhone())
                .build();
    }

}
