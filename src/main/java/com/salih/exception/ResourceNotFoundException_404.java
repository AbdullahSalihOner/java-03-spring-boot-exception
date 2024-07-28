package com.salih.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// This annotation is used to indicate that the method return value should be bound to the web response body.
@ResponseStatus( value = HttpStatus.NOT_FOUND) // 404 Not Found
public class ResourceNotFoundException_404 extends Exception {

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException_404(String message) {
        super(message);
    }
}
