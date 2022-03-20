package com.epam.fitness.dao.impl;

import com.epam.fitness.dao.AbstractDao;
import com.epam.fitness.dao.UserDao;
import com.epam.fitness.entity.User;
import com.epam.fitness.exception.DaoException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private static final String FIND_BY_LOGIN_AND_PASSWORD = "SELECT * FROM user WHERE login=? AND password=?";
    private static final String FIND_USER_BY_ID = "SELECT * FROM user WHERE id=?";

    public UserDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        return executeForSingleResult(FIND_BY_LOGIN_AND_PASSWORD, new UserRowMapper(), login, password);
    }

    @Override
    public Optional<User> getById(Long id) {
        return executeForSingleResult(FIND_USER_BY_ID, new UserRowMapper(), id);
    }

    @Override
    public List<User> getAll() throws DaoException {
        return executeQuery("SELECT * FROM user", new UserRowMapper());
    }

    @Override
    public void save(User item) {
        // TODO: 20.03.2022 Implement this method
    }

    @Override
    public void removeById(Long id) {
        // TODO: 20.03.2022 Implement this method
    }

    @Override
    protected String getTableName() {
        return User.TABLE;
    }


}
