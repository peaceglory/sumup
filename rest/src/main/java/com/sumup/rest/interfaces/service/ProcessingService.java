package com.sumup.rest.interfaces.service;

import com.sumup.rest.exception.definitions.ProcessingException;

public interface ProcessingService<T, R> {
    R process(T data) throws ProcessingException;
}
