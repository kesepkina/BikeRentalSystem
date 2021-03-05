package com.epam.brs.model.dao.impl;

import com.epam.brs.model.dao.UserDao;
import com.epam.brs.model.dao.DaoException;
import com.epam.brs.model.entity.User;
import com.epam.brs.model.entity.UserRole;
import com.epam.brs.model.pool.ConnectionPool;
import com.epam.brs.util.Encryptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.intellij.lang.annotations.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private static final Logger logger = LogManager.getLogger();

    private static final UserDao instance = new UserDaoImpl();

    @Language("MySQL")
    private static final String ADD_USER_SQL_QUERY = "INSERT INTO users(login, passwordHash, name, surname, email) VALUES (?, ?, ?, ?, ?)";
    @Language("MySQL")
    private static final String CONTAINS_LOGIN_SQL_QUERY = "SELECT 1 FROM users WHERE login=?";
    @Language("MySQL")
    private static final String FIND_USER_SQL_QUERY = "SELECT passwordHash, name, surname, email, role FROM users WHERE login=?";

    private UserDaoImpl() {
    }

    public static UserDao getInstance() {
        return instance;
    }

    @Override
    public boolean containsLogin(String login) throws DaoException {
        boolean contains = false;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(CONTAINS_LOGIN_SQL_QUERY)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                contains = true;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return contains;
    }

    @Override
    public boolean contains(String username, String password) {
        //TODO
        boolean contains = false;

        return contains;
    }

    @Override
    public Optional<User> findUser(String login, String password) throws DaoException {
        Optional<User> optionalUser;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_SQL_QUERY)) {
            statement.setString(1, login);
            ResultSet userData = statement.executeQuery();
            if (userData.next()) {
                String hashedPassword = userData.getNString(1);
                if (Encryptor.coincide(password, hashedPassword)) {
                    String name = userData.getNString(2);
                    String surname = userData.getNString(3);
                    String email = userData.getNString(4);
                    String role = userData.getNString(5);
                    UserRole userRole = UserRole.valueOf(role);
                    User user = new User(login, name, surname, email, userRole);
                    optionalUser = Optional.of(user);
                } else {
                    logger.error("Incorrect password inputted for login {}", login);
                    optionalUser = Optional.empty();
                }
            } else {
                optionalUser = Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return optionalUser;
    }

    @Override
    public boolean addUser(User user, String hashedPassword) throws DaoException {
        boolean addedSuccessfully;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_USER_SQL_QUERY)) {
            String login = user.getLogin();
            String name = user.getName();
            String surname = user.getSurname();
            String email = user.getEmail();
            statement.setString(1, login);
            statement.setString(2, hashedPassword);
            statement.setString(3, name);
            statement.setString(4, surname);
            statement.setString(5, email);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                addedSuccessfully = true;
                logger.info("User added successfully");
            } else {
                logger.error("User {} wasn't added", user);
                addedSuccessfully = false;
            }
        } catch (SQLException e) {
            logger.error("Exception while adding new user", e);
            throw new DaoException("Exception while adding new user", e);
        }
        return addedSuccessfully;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User find(Integer id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean delete(User entity) {
        return false;
    }

    @Override
    public User update(User entity) {
        return null;
    }
}
