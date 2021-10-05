package com.tuum.banking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidAmountException extends ResponseStatusException {
    //negative amount
    public InvalidAmountException() {
        super(HttpStatus.BAD_REQUEST, "Invalid Amount!");
    }
}
