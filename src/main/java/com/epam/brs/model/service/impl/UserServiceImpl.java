package com.epam.brs.model.service.impl;

import com.epam.brs.model.dao.impl.UserDaoImpl;
import com.epam.brs.model.entity.User;
import com.epam.brs.model.service.UserService;
import com.epam.brs.validator.UserDataValidator;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserDataValidator userDataValidator = new UserDataValidator();

    @Override
    public boolean checkUser(String username, String password) {
        boolean exists = UserDaoImpl.getInstance().contains(username, password);
        return exists;
    }

    @Override
    public boolean checkLogin(String username) {
        boolean exists = UserDaoImpl.getInstance().containsUsername(username);
        return exists;
    }

    @Override
    public void addUser(String name, String email, String username, String password) {
        User user = new User(name, email, username, password);
        UserDaoImpl.getInstance().addUser(user);
    }

    @Override
    public String getName(String username) {
        Optional<User> optionalUser = UserDaoImpl.getInstance().getUser(username);
        String name;
        if (optionalUser.isPresent()) {
            name = optionalUser.get().getName();
        } else {
            name = "";
        }
        return name;
    }

    @Override
    public boolean isLogin(String usernameValue) {
        boolean isCorrect = false;
        if (userDataValidator.isLogin(usernameValue)) {
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
