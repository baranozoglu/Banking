package com.tuum.banking.service;

import com.tuum.banking.converter.CustomerConverter;
import com.tuum.banking.dto.request.CustomerRequest;
import com.tuum.banking.dto.response.CustomerResponse;
import com.tuum.banking.mapper.CustomerMapper;
import com.tuum.banking.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CustomerService {

    final private CustomerMapper customerMapper;
    final private CustomerConverter customerConverter;

    public CustomerResponse getCustomer(Long id) {
        return customerConverter.entityToResponse(customerMapper.findById(id));
    }

    public CustomerResponse addCustomer(CustomerRequest request) {
        Long _id = customerMapper.addCustomer(customerConverter.requestToEntity(request));
        return CustomerResponse.builder()
                .id(_id)
                .build();
    }

    public CustomerResponse updateCustomer(CustomerRequest request) {
        Long _id = customerMapper.updateCustomer(customerConverter.requestToEntity(request));
        return CustomerResponse.builder()
                .id(_id)
                .build();
    }

    public CustomerResponse deleteCustomer(Long id) {
        Long _id = customerMapper.deleteCustomer(id);
        return CustomerResponse.builder()
                .id(_id)
                .build();
    }


}
