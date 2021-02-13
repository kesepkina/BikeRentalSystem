package com.epam.brs.model.dao;

import com.epam.brs.model.entity.User;

import java.util.Optional;

public interface UserDao {

    boolean containsLogin(String username) throws InterruptedException, DaoException;

    boolean contains(String username, String password);

    Optional<User> findUser(String login, String password);

    boolean addUser(User user, String hashedPassword) throws DaoException;
}
