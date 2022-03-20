package com.epam.fitness.dao;

import com.epam.fitness.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> getById(Long id) throws DaoException;

    List<T> getAll() throws DaoException;

    void save(T item);

    void removeById(Long id);
}
