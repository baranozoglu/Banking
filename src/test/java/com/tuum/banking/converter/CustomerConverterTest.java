package com.tuum.banking.converter;

import com.tuum.banking.dto.request.CustomerRequest;
import com.tuum.banking.dto.response.CustomerResponse;
import com.tuum.banking.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CustomerConverterTest {

    @InjectMocks
    private CustomerConverter converter;

    @Test
    public void shouldSuccessfullyConvertRequestToEntity() {
        final CustomerRequest request = CustomerRequest.builder()
                .id(1L)
                .email("abaranozoglu@gmail.com")
                .name("Baran")
                .surname("Ozoglu")
                .phone(5459475193L)
                .build();

        final Customer customer = converter.requestToEntity(request);
        assertEquals(customer.getId(), request.getId());
        assertEquals(customer.getEmail(), request.getEmail());
        assertEquals(customer.getName(), request.getName());
        assertEquals(customer.getSurname(), request.getSurname());
        assertEquals(customer.getPhone(), request.getPhone());
    }

    @Test
    public void shouldSuccessfullyConvertEntityToResponse() {
        final Customer entity = Customer.builder()
                .id(1L)
                .email("abaranozoglu@gmail.com")
                .name("Baran")
                .surname("Ozoglu")
                .phone(5459475193L)
                .build();

        final CustomerResponse response = converter.entityToResponse(entity);
        assertEquals(response.getId(), entity.getId());
        assertEquals(response.getEmail(), entity.getEmail());
        assertEquals(response.getName(), entity.getName());
        assertEquals(response.getSurname(), entity.getSurname());
        assertEquals(response.getPhone(), entity.getPhone());
    }
}
