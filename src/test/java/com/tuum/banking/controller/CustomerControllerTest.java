package com.tuum.banking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuum.banking.dto.request.CustomerRequest;
import com.tuum.banking.dto.response.CustomerResponse;
import com.tuum.banking.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    @MockBean
    private CustomerService customerService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void should_getCustomer_successfully() throws Exception {
        final CustomerResponse response = CustomerResponse.builder()
                .id(1L)
                .email("abaranozoglu@gmail.com")
                .name("Baran")
                .surname("Ozoglu")
                .phone(5459475193L)
                .build();

        when(customerService.getCustomer(any())).thenReturn(response);

        mockMvc.perform(get("/customers/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customerService).getCustomer(anyLong());

    }

    @Test
    public void should_addCustomer_successfully() throws Exception {
        final CustomerRequest request = CustomerRequest.builder()
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

        String jsonStr = objectMapper.writeValueAsString(request);

        when(customerService.addCustomer(any(CustomerRequest.class))).thenReturn(response);

        mockMvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonStr))
                .andExpect(status().isOk());

        verify(customerService).addCustomer(any(CustomerRequest.class));
    }

    @Test
    public void should_updateCustomer_successfully() throws Exception {
        final CustomerRequest request = CustomerRequest.builder()
                .id(1L)
                .email("abaranozoglu@gmail.com")
                .name("Baran2")
                .surname("Ozoglu")
                .phone(5459475193L)
                .build();

        final CustomerResponse response = CustomerResponse.builder()
                .id(1L)
                .email("abaranozoglu@gmail.com")
                .name("Baran2")
                .surname("Ozoglu")
                .phone(5459475193L)
                .build();

        String jsonStr = objectMapper.writeValueAsString(request);

        when(customerService.updateCustomer(any(CustomerRequest.class))).thenReturn(response);

        mockMvc.perform(put("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonStr))
                .andExpect(status().isOk());

        verify(customerService).updateCustomer(any(CustomerRequest.class));
    }

    @Test
    public void should_deleteCustomer_successfully() throws Exception {
        final CustomerResponse response = CustomerResponse.builder()
                .id(1L)
                .build();

        when(customerService.deleteCustomer(any())).thenReturn(response);

        mockMvc.perform(delete("/customers/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customerService).deleteCustomer(anyLong());
    }
}
