package com.epam.brs.model.dao;

import com.epam.brs.model.entity.User;

import java.util.Optional;

public interface UserDao extends BaseDao<Integer, User> {

    boolean containsLogin(String username) throws DaoException;

    Optional<User> findUser(String login, String password) throws DaoException;

    boolean addUser(User user, String hashedPassword) throws DaoException;
}
