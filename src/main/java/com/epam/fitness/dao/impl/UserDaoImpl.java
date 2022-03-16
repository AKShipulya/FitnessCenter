package com.epam.fitness.dao.impl;

import com.epam.fitness.dao.UserDao;
import com.epam.fitness.entity.User;
import com.epam.fitness.exception.DaoException;
import com.epam.fitness.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static com.epam.fitness.dao.ColumnName.*;

public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String INSERT_NEW_USER = "INSERT INTO user (id, login, email, password, name, surname, role, status, payment) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_USER = "UPDATE user SET login=?,email=?,password=?, name=?,surname=?,role=?,status=?,payment=? WHERE id=?";
    private static final String FIND_USER_BY_EMAIL = "SELECT user.id FROM user WHERE user.email=?";
    private static final String FIND_USER_BY_EMAIL_QUERY = "SELECT * FROM user WHERE user.email=?";
    private static final String FIND_USER_BY_LOGIN_RETURNS_ID = "SELECT user.id FROM user WHERE user.login=?";
    private static final String UPDATE_USER_STATUS_BY_USER_ID = "UPDATE user SET status=? WHERE id=?";
    private static final String UPDATE_USER_ROLE_BY_USER_ID = "UPDATE user SET role=? WHERE id=?";
    private static final String UPDATE_USER_EMAIL_BY_ID = "UPDATE user SET email=? WHERE id=?";
    private static final String UPDATE_USER_LOGIN_BY_ID = "UPDATE user SET login=? WHERE id=?";
    private static final String FIND_USER_STATUS_BY_ID = "SELECT status FROM user  WHERE user.id=?";
    private static final String FIND_USER_BY_ID = "SELECT * FROM user WHERE user.id = ?";
    private static final String FIND_ALL_USER = "SELECT * FROM user";

    @Override
    public Optional<User> findEntityById(long id) throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    optionalUser = Optional.of(takeUserInfo(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Exception in method findEntityById of class UserDaoImpl" + e);
            throw new DaoException("Exception in method findEntityById of class UserDaoImpl" + e);
        }
        return optionalUser;
    }

    @Override
    public List<User> findAllEntities() throws DaoException {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_USER)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    User user = takeUserInfo(resultSet);
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Exception in method findAllEntities of class UserDaoImpl" + e);
            throw new DaoException("Exception in method findAllEntities of class UserDaoImpl" + e);
        }
        return users;
    }

    @Override
    public long insertNewEntity(User entity) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_USER, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getEmail());
            preparedStatement.setString(3, entity.getPasswordHash());
            preparedStatement.setString(4, entity.getName());
            preparedStatement.setString(5, entity.getSurname());
            preparedStatement.setString(6, String.valueOf(entity.getRole()).toLowerCase(Locale.ROOT));
            preparedStatement.setString(7, String.valueOf(entity.getStatus()).toLowerCase(Locale.ROOT));
            preparedStatement.setInt(8, entity.getPayment());
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                resultSet.next();
                return resultSet.getLong(1);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception in method insertNewEntity of class UserDaoImpl" + e);
            throw new DaoException("Exception in method insertNewEntity of class UserDaoImpl" + e);
        }
    }

    @Override
    public boolean updateEntity(User entity) throws DaoException {
        int result;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getEmail());
            preparedStatement.setString(3, entity.getPasswordHash());
            preparedStatement.setString(4, entity.getName());
            preparedStatement.setString(5, entity.getSurname());
            preparedStatement.setString(6, String.valueOf(entity.getRole()).toLowerCase(Locale.ROOT));
            preparedStatement.setString(7, String.valueOf(entity.getStatus()).toLowerCase(Locale.ROOT));
            preparedStatement.setInt(8, entity.getPayment());
            preparedStatement.setLong(9, entity.getId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Exception in method updateEntity of class UserDaoImpl" + e);
            throw new DaoException("Exception in method updateEntity of class UserDaoImpl" + e);
        }
        return (result > 0);
    }

    @Override
    public boolean isLoginExist(String login) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_LOGIN_RETURNS_ID)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                boolean result = resultSet.isBeforeFirst();
                return result;
            }
        } catch (SQLException e) {
            LOGGER.error("Exception in method isLoginExist of class UserDaoImpl" + e);
            throw new DaoException("Exception in method isLoginExist of class UserDaoImpl" + e);
        }
    }

    @Override
    public boolean isEmailExist(String email) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_EMAIL)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                boolean result = resultSet.isBeforeFirst();
                return result;
            }
        } catch (SQLException e) {
            LOGGER.error("Exception in method isEmailExist of class UserDaoImpl" + e);
            throw new DaoException("Exception in method isEmailExist of class UserDaoImpl" + e);
        }
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws DaoException {
        Optional<User> user = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_EMAIL_QUERY)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    user = Optional.of(takeUserInfo(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Exception in method findUserByEmail of class UserDaoImpl" + e);
            throw new DaoException("Exception in method findUserByEmail of class UserDaoImpl" + e);
        }
        return user;
    }

    @Override
    public boolean updateUserEmail(long userId, String email) throws DaoException {
        boolean result;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_EMAIL_BY_ID)) {
            preparedStatement.setString(1, email);
            preparedStatement.setLong(2, userId);
            result = preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            LOGGER.error("Exception in method updateUserEmail of class UserDaoImpl" + e);
            throw new DaoException("Exception in method updateUserEmail of class UserDaoImpl" + e);
        }
        return result;
    }

    @Override
    public boolean updateUserLogin(long userId, String login) throws DaoException {
        boolean result;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_LOGIN_BY_ID)) {
            preparedStatement.setString(1, login);
            preparedStatement.setLong(2, userId);
            result = preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            LOGGER.error("Exception in method updateUserLogin of class UserDaoImpl" + e);
            throw new DaoException("Exception in method updateUserLogin of class UserDaoImpl" + e);
        }
        return result;
    }

    @Override
    public boolean updateUserStatusById(long userId, User.Status status) throws DaoException {
        boolean result;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_STATUS_BY_USER_ID)) {
            preparedStatement.setString(1, String.valueOf(status).toLowerCase(Locale.ROOT));
            preparedStatement.setLong(2, userId);
            result = preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            LOGGER.error("Exception in method updateUserStatusById of class UserDaoImpl" + e);
            throw new DaoException("Exception in method updateUserStatusById of class UserDaoImpl" + e);
        }
        return result;
    }

    @Override
    public boolean updateUserRoleById(long userId, User.Role role) throws DaoException {
        boolean result;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_ROLE_BY_USER_ID)) {
            preparedStatement.setString(1, String.valueOf(role).toLowerCase(Locale.ROOT));
            preparedStatement.setLong(2, userId);
            result = preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            LOGGER.error("Exception in method updateUserStatusById of class UserDaoImpl" + e);
            throw new DaoException("Exception in method updateUserStatusById of class UserDaoImpl" + e);
        }
        return result;
    }

    public User takeUserInfo(ResultSet resultSet) throws SQLException {
        return (new User.UserBuilder()
                .setId(resultSet.getLong(USER_ID))
                .setRole(User.Role.valueOf(resultSet.getString(USER_ROLE).toUpperCase(Locale.ROOT)))
                .setStatus(User.Status.valueOf(resultSet.getString(USER_STATUS).toUpperCase(Locale.ROOT)))
                .setLogin(resultSet.getString(LOGIN))
                .setPassword(resultSet.getString(PASSWORD))
                .setName(resultSet.getString(USER_NAME))
                .setSurname(resultSet.getString(USER_SURNAME))
                .setEmail(resultSet.getString(EMAIL))
                .buildUser());
    }
}
