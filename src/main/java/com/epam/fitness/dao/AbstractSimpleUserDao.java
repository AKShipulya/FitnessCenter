package com.epam.fitness.dao;

import com.epam.fitness.entity.Identifiable;
import com.epam.fitness.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AbstractSimpleUserDao<T extends Identifiable> implements SimpleUserDao<T> {

    private final Connection connection;

    public AbstractSimpleUserDao(Connection connection) {
        this.connection = connection;
    }

    protected List<T> executeQuery(String query, RowMapper<T> rowMapper, Object... parameters) throws DaoException {
        try (PreparedStatement preparedStatement = buildPreparedStatement(query, parameters)){
            ResultSet resultSet = preparedStatement.executeQuery();
            List<T> entities = new ArrayList<>();
            while (resultSet.next())
        }
    }
}
