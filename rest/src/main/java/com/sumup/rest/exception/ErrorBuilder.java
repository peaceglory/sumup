package com.sumup.rest.exception;

import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.stream.Collectors;

class ErrorBuilder {

    Error constructError(List<ObjectError> errors) {
        return new Error(errors.stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList()));
    }
}
