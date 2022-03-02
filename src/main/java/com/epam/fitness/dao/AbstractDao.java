package com.epam.fitness.dao;

import com.epam.fitness.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class AbstractDao {

    protected static final Logger LOGGER = LogManager.getLogger();
    protected static final String DEFAULT_CONCAT_DELIMITER = ",";
    protected Connection connection;

    public abstract List<T> findAll() throws DaoException;

    public abstract Optional<T> findById(long id) throws DaoException;

    public abstract boolean create(T entity) throws DaoException;

    public abstract boolean update(T entity) throws DaoException;

    public abstract boolean deleteById(long id) throws DaoException;

    public boolean delete(T entity) throws DaoException {
        return deleteById(entity.getId());
    }

    protected abstract T mapEntity(ResultSet resultSet) throws DaoException;

    protected abstract void setPreparedStatement(PreparedStatement statement, T entity) throws DaoException;

    void setConnection (Connection connection){
        this.connection = connection;
    }
}
