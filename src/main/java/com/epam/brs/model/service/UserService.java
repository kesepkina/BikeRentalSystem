package com.epam.brs.model.service;

import com.epam.brs.model.entity.User;

import java.util.Map;
import java.util.Optional;

public interface UserService {

    Optional<User> login(String login, String password) throws ServiceException;

    boolean signUp(Map<String, String> userData) throws ServiceException;

    void sendMail(String addressee, String topic, String text) throws ServiceException;

    boolean updatePhotoName(String photoName, String login) throws ServiceException;
}
