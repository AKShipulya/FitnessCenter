package com.epam.fitness.dao;

import com.epam.fitness.entity.User;
import com.epam.fitness.exception.DaoException;

import java.util.Optional;

public interface UserDao {

    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;
}
