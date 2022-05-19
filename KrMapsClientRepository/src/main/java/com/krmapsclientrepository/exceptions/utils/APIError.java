package com.krmapsclientrepository.exceptions.utils;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@Data
public class APIError {

    private final HttpStatus status;
    private final String message;
    private final List<String> errors;

    public APIError(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Collections.singletonList(error);
    }

}