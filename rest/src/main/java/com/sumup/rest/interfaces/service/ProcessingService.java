package com.sumup.rest.interfaces.service;

public interface ProcessingService<T, R> {
    R process(T data);
}
