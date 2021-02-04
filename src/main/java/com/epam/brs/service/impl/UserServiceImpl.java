package com.epam.brs.service.impl;

import com.epam.brs.dao.impl.UserDaoImpl;
import com.epam.brs.entity.User;
import com.epam.brs.service.UserService;
import com.epam.brs.validator.UserDataValidator;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserDataValidator userDataValidator = new UserDataValidator();
    private final UserDaoImpl userDao = new UserDaoImpl();

    @Override
    public boolean checkUser(String username, String password) {
        boolean exists = userDao.contains(username, password);
        return exists;
    }

    @Override
    public boolean checkUsername(String username) {
        boolean exists = userDao.containsUsername(username);
        return exists;
    }

    @Override
    public void addUser(String name, String email, String username, String password) {
        User user = new User(name, email, username, password);
        userDao.addUser(user);
    }

    @Override
    public String getName(String username) {
        Optional<User> optionalUser = userDao.getUser(username);
        String name;
        if (optionalUser.isPresent()) {
            name = optionalUser.get().getName();
        } else {
            name = "";
        }
        return name;
    }

    @Override
    public boolean isUsername(String usernameValue) {
        boolean isCorrect = false;
        if (userDataValidator.isUsername(usernameValue)) {
            isCorrect = true;
        }
        return isCorrect;
    }

    @Override
    public boolean isPassword(String passwordValue) {
        boolean isCorrect = false;
        if (userDataValidator.isPassword(passwordValue)) {
            isCorrect = true;
        }
        return isCorrect;
    }

    @Override
    public boolean isEmail(String emailValue) {
        boolean isCorrect = false;
        if (userDataValidator.isPassword(emailValue)) {
            isCorrect = true;
        }
        return isCorrect;
    }
}
