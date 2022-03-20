package com.epam.fitness.service;

import com.epam.fitness.dao.DaoHelper;
import com.epam.fitness.dao.DaoHelperFactory;
import com.epam.fitness.dao.UserDao;
import com.epam.fitness.entity.User;
import com.epam.fitness.exception.DaoException;
import com.epam.fitness.exception.ServiceException;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final DaoHelperFactory daoHelperFactory;

    public UserServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public Optional<User> login(String login, String password) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserDao dao = helper.createUserDao();
            Optional<User> user = dao.findUserByLoginAndPassword(login, password);
            helper.endTransaction();
            return user;
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }
}
