package com.sumup.rest.interfaces.repository;

public interface DataAccessRepository<T> {
    T push(T data);
}
