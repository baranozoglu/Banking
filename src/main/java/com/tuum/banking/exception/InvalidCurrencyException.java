package com.tuum.banking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidCurrencyException extends ResponseStatusException {

    public InvalidCurrencyException() {
        super(HttpStatus.BAD_REQUEST, "Invalid Currency!");
    }
}
