package com.epam.brs.model.service;

import com.epam.brs.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {

    boolean downloadTableAsJSON() throws ServiceException;

    List<User> findAll() throws ServiceException;

    Optional<User> login(String login, String password) throws ServiceException;

    boolean signUp(Map<String, String> userData) throws ServiceException;

    void sendMail(String addressee, String topic, String text) throws ServiceException;

    boolean updatePhotoName(String photoName, String login) throws ServiceException;
}
