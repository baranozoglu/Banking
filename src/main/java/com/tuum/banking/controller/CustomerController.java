package com.tuum.banking.controller;

import com.tuum.banking.dto.request.CustomerRequest;
import com.tuum.banking.dto.response.CustomerResponse;
import com.tuum.banking.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/customers")
public class CustomerController {

    final private CustomerService customerService;

    @GetMapping("/{id}")
    public CustomerResponse getCustomer(@PathVariable(value="id") Long id) {
        return customerService.getCustomer(id);
    }

    @PostMapping()
    public CustomerResponse addCustomer(@RequestBody CustomerRequest request) {
        return customerService.addCustomer(request);
    }

    @PutMapping()
    public CustomerResponse updateCustomer(@RequestBody CustomerRequest request) {
        return customerService.updateCustomer(request);
    }

    @DeleteMapping("/{id}")
    public CustomerResponse deleteCustomer(@PathVariable(value="id") Long id) {
        return customerService.deleteCustomer(id);
    }

}
