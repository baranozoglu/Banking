package com.tuum.banking.service;

import com.tuum.banking.converter.CustomerConverter;
import com.tuum.banking.dto.request.CustomerRequest;
import com.tuum.banking.dto.response.CustomerResponse;
import com.tuum.banking.exception.GeneralException;
import com.tuum.banking.exception.NotFoundException;
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
        final Customer customer = customerMapper.findById(id);
        if(!Objects.nonNull(customer)) {
            throw new NotFoundException();
        }
        return customerConverter.entityToResponse(customer);
    }

    public CustomerResponse addCustomer(CustomerRequest request) {
        try {
            final Long _id = customerMapper.addCustomer(customerConverter.requestToEntity(request));
            final Customer customer = customerMapper.findById(_id);
            return customerConverter.entityToResponse(customer);
        } catch (Exception e) {
            throw new GeneralException();
        }
    }

    public CustomerResponse updateCustomer(CustomerRequest request) {
        try {
            final Long _id = customerMapper.updateCustomer(customerConverter.requestToEntity(request));
            final Customer customer = customerMapper.findById(_id);
            return customerConverter.entityToResponse(customer);
        } catch (Exception e) {
            throw new GeneralException();
        }
    }

    public CustomerResponse deleteCustomer(Long id) {
        try {
            final Long _id = customerMapper.deleteCustomer(id);
            return CustomerResponse.builder()
                    .id(_id)
                    .build();
        } catch (Exception e) {
            throw new GeneralException();
        }
    }


}
