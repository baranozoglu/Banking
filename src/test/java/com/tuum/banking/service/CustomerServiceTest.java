package com.tuum.banking.service;

import com.tuum.banking.converter.CustomerConverter;
import com.tuum.banking.dto.request.CustomerRequest;
import com.tuum.banking.dto.response.CustomerResponse;
import com.tuum.banking.exception.NotFoundException;
import com.tuum.banking.mapper.CustomerMapper;
import com.tuum.banking.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {
    @Mock
    private CustomerMapper customerMapper;
    @Mock
    private CustomerConverter customerConverter;
    @InjectMocks
    private CustomerService customerService;

    @Test
    public void should_getCustomer_Successfully() {
        final Long customerId = 1L;
        final Customer entity = Customer.builder()
                .id(1L)
                .email("abaranozoglu@gmail.com")
                .name("Baran")
                .surname("Ozoglu")
                .phone(5459475193L)
                .build();
        final CustomerResponse response = CustomerResponse.builder()
                .id(1L)
                .email("abaranozoglu@gmail.com")
                .name("Baran")
                .surname("Ozoglu")
                .phone(5459475193L)
                .build();

        when(customerMapper.findById(customerId)).thenReturn(entity);
        when(customerConverter.entityToResponse(any(Customer.class))).thenReturn(response);

        CustomerResponse customerResponse = customerService.getCustomer(customerId);

        assertEquals(customerResponse.getId(), response.getId());
        assertEquals(customerResponse.getEmail(), response.getEmail());
        assertEquals(customerResponse.getName(), response.getName());
        assertEquals(customerResponse.getSurname(), response.getSurname());
        assertEquals(customerResponse.getPhone(), response.getPhone());
    }

    @Test(expected = NotFoundException.class)
    public void should_getCustomer_ThrowNotFoundException() {
        final Long customerId = 1L;

        final CustomerResponse response = CustomerResponse.builder()
                .id(1L)
                .email("abaranozoglu@gmail.com")
                .name("Baran")
                .surname("Ozoglu")
                .phone(5459475193L)
                .build();

        when(customerMapper.findById(customerId)).thenReturn(null);
        when(customerConverter.entityToResponse(any(Customer.class))).thenReturn(response);

        CustomerResponse customerResponse = customerService.getCustomer(customerId);

        assertEquals(customerResponse.getId(), response.getId());
        assertEquals(customerResponse.getEmail(), response.getEmail());
        assertEquals(customerResponse.getName(), response.getName());
        assertEquals(customerResponse.getSurname(), response.getSurname());
        assertEquals(customerResponse.getPhone(), response.getPhone());
    }

    @Test
    public void should_addCustomer_Successfully() {
        final CustomerRequest request = CustomerRequest.builder()
                .email("abaranozoglu@gmail.com")
                .name("Baran")
                .surname("Ozoglu")
                .phone(5459475193L)
                .build();
        final CustomerResponse response = CustomerResponse.builder()
                .id(1L)
                .email("abaranozoglu@gmail.com")
                .name("Baran")
                .surname("Ozoglu")
                .phone(5459475193L)
                .build();
        final Customer entity = Customer.builder()
                .id(1L)
                .email("abaranozoglu@gmail.com")
                .name("Baran")
                .surname("Ozoglu")
                .phone(5459475193L)
                .build();
        when(customerConverter.requestToEntity(any(CustomerRequest.class))).thenReturn(entity);
        when(customerMapper.addCustomer(any(Customer.class))).thenReturn(1L);
        when(customerMapper.findById(anyLong())).thenReturn(entity);
        when(customerConverter.entityToResponse(any(Customer.class))).thenReturn(response);

        CustomerResponse customerResponse = customerService.addCustomer(request);

        assertEquals(customerResponse.getPhone(), request.getPhone());
        assertEquals(customerResponse.getName(), request.getName());
        assertEquals(customerResponse.getEmail(), request.getEmail());
        assertEquals(customerResponse.getSurname(), request.getSurname());
    }

    @Test
    public void should_updateCustomer_Successfully() {
        final CustomerRequest request = CustomerRequest.builder()
                .email("abaranozoglu@gmail.com")
                .name("Baran")
                .surname("Ozoglu")
                .phone(5459475193L)
                .build();
        final CustomerResponse response = CustomerResponse.builder()
                .id(1L)
                .email("abaranozoglu@gmail.com")
                .name("Baran")
                .surname("Ozoglu")
                .phone(5459475193L)
                .build();
        final Customer entity = Customer.builder()
                .id(1L)
                .email("abaranozoglu@gmail.com")
                .name("Baran")
                .surname("Ozoglu")
                .phone(5459475193L)
                .build();
        when(customerConverter.requestToEntity(any(CustomerRequest.class))).thenReturn(entity);
        when(customerMapper.updateCustomer(any(Customer.class))).thenReturn(1L);
        when(customerMapper.findById(anyLong())).thenReturn(entity);
        when(customerConverter.entityToResponse(any(Customer.class))).thenReturn(response);

        CustomerResponse customerResponse = customerService.updateCustomer(request);

        assertEquals(customerResponse.getPhone(), request.getPhone());
        assertEquals(customerResponse.getName(), request.getName());
        assertEquals(customerResponse.getEmail(), request.getEmail());
        assertEquals(customerResponse.getSurname(), request.getSurname());
    }

    @Test
    public void should_deleteCustomer_Successfully() {
        final Long customerId = 1L;

        when(customerMapper.deleteCustomer(anyLong())).thenReturn(customerId);
        CustomerResponse customerResponse = customerService.deleteCustomer(customerId);

        assertEquals(customerResponse.getId(), customerId);

    }
}
