package com.epam.brs.model.dao.impl;

import com.epam.brs.model.dao.AbstractDao;
import com.epam.brs.model.dao.UserDao;
import com.epam.brs.model.dao.exception.DaoException;
import com.epam.brs.model.entity.User;
import com.epam.brs.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

    private static final Logger logger = LogManager.getLogger();

    private static final UserDao instance = new UserDaoImpl();

    private static final String ADD_USER_SQL_QUERY = "INSERT INTO users(login, password, name, surname, email) VALUES (?, ?, ?, ?, ?)";
    private static final String CONTAINS_USERNAME_SQL_QUERY = "SELECT 1 FROM users WHERE login=?";

    private UserDaoImpl() {
    }

    public static UserDao getInstance() {
        return instance;
    }

    @Override
    public boolean containsUsername(String login) throws InterruptedException, DaoException {
        boolean contains = false;
        try(Connection connection = ConnectionPool.INSTANCE.getConnection().orElseThrow();
            PreparedStatement statement = connection.prepareStatement(CONTAINS_USERNAME_SQL_QUERY)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
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
    public boolean add(User entity) throws DaoException {
        boolean addedSuccessfully = false;
        try(Connection connection = ConnectionPool.INSTANCE.getConnection().orElseThrow();
            PreparedStatement statement = connection.prepareStatement(ADD_USER_SQL_QUERY)) {
            String login = entity.getLogin();
            String name = entity.getName();
            String surname = entity.getSurname();
            String email = entity.getEmail();
            String hashedPassword = entity.getHashedPassword();
            statement.setString(1, login);
            statement.setString(2, hashedPassword);
            statement.setString(3, name);
            statement.setString(4, surname);
            statement.setString(5, email);
            addedSuccessfully = statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Exception by creating new prepare statement",e);
        } catch (InterruptedException e) {
            logger.warn("Thread was interrupted while taking new free connection", e);
            Thread.currentThread().interrupt();
            // TODO: how to do correctly?
        }
        return addedSuccessfully;
    }

    @Override
    public User update(User entity) {
        return null;
    }
}
