package com.epam.fitness.dao;

import com.epam.fitness.entity.User;
import com.epam.fitness.exception.DaoException;

import java.util.Optional;

/**
 * The interface represent user dao
 */
public interface UserDao extends AbstractDao<User>{

    /**
     * Check existing of login
     *
     * @param login the user login
     * @return true if exist
     * @throws DaoException
     */
    boolean isLoginExist(String login) throws DaoException;

    /**
     * Check existing of email
     *
     * @param email the user email
     * @return true if exist
     * @throws DaoException
     */
    boolean isEmailExist(String email) throws DaoException;

    /**
     * Find user by email
     *
     * @param email the user email
     * @return optional of user
     * @throws DaoException
     */
    Optional<User> findUserByEmail(String email) throws DaoException;

    /**
     * Update user email
     *
     * @param userId the user id
     * @param email  the user email
     * @return true if successful
     * @throws DaoException
     */
    boolean updateUserEmail(long userId, String email) throws DaoException;

    /**
     * Update user login
     *
     * @param userId the user id
     * @param login  the user login
     * @return true if successful
     * @throws DaoException
     */
    boolean updateUserLogin(long userId, String login) throws DaoException;

    /**
     * Update user status
     *
     * @param userId the user id
     * @param status the user status
     * @return true if successful
     * @throws DaoException
     */
    boolean updateUserStatusById(long userId, User.Status status) throws DaoException;

    /**
     * Update user role
     *
     * @param userId the user id
     * @param status the user status
     * @return true if successful
     * @throws DaoException
     */
    boolean updateUserRoleById(long userId, User.Role status) throws DaoException;
}
