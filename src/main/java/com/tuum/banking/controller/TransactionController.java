package com.tuum.banking.controller;

import com.tuum.banking.dto.request.TransactionRequest;
import com.tuum.banking.dto.response.TransactionResponse;
import com.tuum.banking.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/transactions")
public class TransactionController {

    final private TransactionService transactionService;

    @GetMapping("/{accountId}")
    public List<TransactionResponse> getTransactionByAccountId(@PathVariable(value="accountId") Long accountId) {
        return transactionService.getTransactionByAccountId(accountId);
    }

    @PostMapping()
    public TransactionResponse addTransaction(@RequestBody TransactionRequest request) {
        return transactionService.addTransaction(request);
    }

    @PostMapping("/rabbit")
    public void addTransactionRabbit(@RequestBody TransactionRequest request) {
        transactionService.transactionStart(request);
    }

    @DeleteMapping("/{id}")
    public TransactionResponse deleteTransaction(@PathVariable(value="id") Long id) {
        return transactionService.deleteTransaction(id);
    }

}
