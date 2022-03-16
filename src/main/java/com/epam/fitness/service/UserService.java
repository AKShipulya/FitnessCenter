package com.epam.fitness.service;

import com.epam.fitness.entity.User;
import com.epam.fitness.exception.ServiceException;

import java.util.Optional;

public interface UserService {

    Optional<User> login(String login, String password) throws ServiceException;
}
