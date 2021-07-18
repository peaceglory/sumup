package com.sumup.rest.interfaces.repository;

import com.sumup.rest.exception.definitions.DataAccessException;

public interface DataAccessRepository<T> {
    T push(T data) throws DataAccessException;
}
