package com.epam.brs.dao.impl;

import com.epam.brs.dao.UserDao;
import com.epam.brs.entity.User;
import com.epam.brs.warehouse.UserWarehouse;

import java.util.Optional;

public class UserDaoImpl implements UserDao {

    @Override
    public void addUser(User user) {
        UserWarehouse.getInstance().add(user);
    }

    @Override
    public boolean containsUsername(String username) {
        boolean contains = UserWarehouse.getInstance().getUser(username).isPresent();
        return contains;
    }

    @Override
    public boolean contains(String username, String password) {
        Optional<User> optionalUser = UserWarehouse.getInstance().getUser(username);
        boolean contains = false;
        if (optionalUser.isPresent()) {
            User foundUser = optionalUser.get();
            contains = foundUser.getPassword().equals(password);
        }
        return contains;
    }

    @Override
    public Optional<User> getUser(String username) {
        Optional<User> optionalUser = UserWarehouse.getInstance().getUser(username);
        return optionalUser;
    }
}
