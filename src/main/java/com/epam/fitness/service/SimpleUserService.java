package com.epam.fitness.service;

import com.epam.fitness.dao.UserDao;
import com.epam.fitness.entity.User;
import com.epam.fitness.exception.DaoException;
import com.epam.fitness.exception.ServiceException;

import java.util.Optional;

public class SimpleUserService implements UserService {

    private final UserDao dao;

    public SimpleUserService(UserDao dao) {
        this.dao = dao;
    }

    @Override
    public Optional<User> login(String login, String password) throws ServiceException {
        try {
            Optional<User> user = dao.findUserByLoginAndPassword(login, password);
            return user;
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }
}
