package com.tuum.banking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidDirectionException extends ResponseStatusException {

    public InvalidDirectionException() {
        super(HttpStatus.BAD_REQUEST, "Invalid Direction!");
    }
}
