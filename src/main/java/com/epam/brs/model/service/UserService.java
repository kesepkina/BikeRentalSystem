package com.epam.brs.model.service;

import com.epam.brs.model.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> login(String login, String password);

    boolean signUp(String name, String surname, String email, String login, String password, String confirmedPassword) throws ServiceException;
}
