package com.epam.brs.dao;

import com.epam.brs.model.entity.User;

import java.util.Optional;

public interface UserDao {
    void addUser(User user);

    boolean containsUsername(String username);

    boolean contains(String username, String password);

    Optional<User> getUser(String username);
}
