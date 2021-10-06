package com.tuum.banking.controller;

import com.tuum.banking.dto.request.AccountRequest;
import com.tuum.banking.dto.response.AccountResponse;
import com.tuum.banking.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/accounts")
public class AccountController {

    final private AccountService accountService;

    @GetMapping("/{id}")
    public AccountResponse getAccount(@PathVariable(value="id") Long id) {
        return accountService.getAccount(id);
    }

    @PostMapping()
    public AccountResponse addAccount(@RequestBody AccountRequest request) {
        return accountService.addAccount(request);
    }

    @PutMapping()
    public AccountResponse updateAccount(@RequestBody AccountRequest request) {
        return accountService.updateAccount(request);
    }

    @DeleteMapping("/{id}")
    public AccountResponse deleteAccount(@PathVariable(value="id") Long id) {
        return accountService.deleteAccount(id);
    }

}
