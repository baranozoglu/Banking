package com.tuum.banking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidAccountException extends ResponseStatusException {
    //negative amount
    public InvalidAccountException() {
        super(HttpStatus.BAD_REQUEST, "Invalid Account!");
    }
}
