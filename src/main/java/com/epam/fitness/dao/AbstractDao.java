package com.epam.fitness.dao;

import com.epam.fitness.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * @param <T>
 */
public interface AbstractDao<T> {
    /**
     * Find entity by id
     *
     * @param id the entity od
     * @return optional of entity
     * @throws DaoException
     */
    Optional<T> findEntityById(long id) throws DaoException;

    /**
     * Find all entities
     *
     * @return list of entity
     * @throws DaoException
     */
    List<T> findAllEntities() throws DaoException;

    /**
     * Insert new entity
     *
     * @param entity the entity
     * @return generated key
     * @throws DaoException
     */
    long insertNewEntity(T entity) throws DaoException;

    /**
     * Update entity if possible
     *
     * @param entity the entity
     * @return true if successful
     * @throws DaoException
     */
    boolean updateEntity(T entity) throws DaoException;
}
