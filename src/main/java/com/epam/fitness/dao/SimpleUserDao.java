package com.epam.fitness.dao;

import com.epam.fitness.entity.User;
import com.epam.fitness.exception.DaoException;

import java.sql.*;
import java.util.Optional;

public class SimpleUserDao implements UserDao {
    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        try {
            Class.forName("org.h2.Driver");
            Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost:9092/~/users", "sa", ""); // TODO: 16.03.2022 check url if it will related a problems with DB
            try (PreparedStatement statement = connection.prepareStatement("SELECT id, login FROM user WHERE login=? AND password=MD5(?)")) {
                statement.setString(1, login);
                statement.setString(2, password);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return Optional.of(new User(resultSet.getLong("id"), resultSet.getString("login")));
                    }
                }
            }
            return Optional.empty();
        } catch (SQLException | ClassNotFoundException exception) {
            throw new DaoException(exception);
        }
    }
}
