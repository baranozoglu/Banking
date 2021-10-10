package com.tuum.banking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DescriptionMissingException extends ResponseStatusException {

    public DescriptionMissingException() {
        super(HttpStatus.BAD_REQUEST, "Description Missing!");
    }
}
