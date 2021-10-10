package com.tuum.banking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class GeneralException extends ResponseStatusException {

    public GeneralException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "It is not you, it's about us!");
    }
}
