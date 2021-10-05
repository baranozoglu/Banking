package com.tuum.banking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AmountMissingException extends ResponseStatusException {

    public AmountMissingException() {
        super(HttpStatus.BAD_REQUEST, "Amount Missing!");
    }
}
