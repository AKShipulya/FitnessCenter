package com.epam.fitness.dao;

import com.epam.fitness.entity.Identifiable;
import com.epam.fitness.exception.DaoException;
import com.epam.fitness.mapper.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T extends Identifiable> implements Dao<T> {

    private final Connection connection;
    private final RowMapper<T> rowMapper;

    public AbstractDao(Connection connection, RowMapper<T> rowMapper) {
        this.connection = connection;
        this.rowMapper = rowMapper;
    }

    protected List<T> executeQuery(String query, Object... parameters) throws DaoException {
        try (PreparedStatement statement = createStatement(query, parameters)) {
            ResultSet resultSet = statement.executeQuery(query);
            List<T> entities = new ArrayList<>();
            while (resultSet.next()) {
                T entity = rowMapper.map(resultSet);
                entities.add(entity);
            }
            return entities;
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
    }

    private PreparedStatement createStatement(String query, Object... params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        for (int i = 1; i <= params.length; i++) {
            statement.setObject(i, params[i - 1]);
        }
        return statement;
    }

    public List<T> getAll() throws DaoException {
        String table = getTableName();
        RowMapper<T> mapper = (RowMapper<T>) RowMapper.create(table);
        return executeQuery("SELECT * FROM " + table, mapper);
    }

    protected Optional<T> executeForSingleResult(String query, Object... params) throws DaoException {
        List<T> items = executeQuery(query, rowMapper, params);
        if (items.size() == 1) {
            return Optional.of(items.get(0));
        } else if (items.size() > 1) {
            throw new IllegalStateException("More than one record found");
        } else {
            return Optional.empty();
        }
    }

    protected abstract String getTableName();
}
