package com.epam.brs.model.service.impl;

import com.epam.brs.model.dao.DaoException;
import com.epam.brs.model.dao.impl.UserDaoImpl;
import com.epam.brs.model.entity.User;
import com.epam.brs.model.service.ServiceException;
import com.epam.brs.model.service.UserService;
import com.epam.brs.util.mail.MailSender;
import com.epam.brs.util.Encryptor;
import com.epam.brs.validator.UserDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger();
    private static final String LOGIN_KEY = "login";
    private static final String EMAIL_KEY = "email";
    private static final String PASSWORD_KEY = "password";
    private static final String NAME_KEY = "name";
    private static final String SURNAME_KEY = "surname";
    private static final String ALREADY_EXISTS = " already exists";
    private static final String MAIL_PROPERTIES_PATH = "properties/config/mail.properties";

    @Override
    public Optional<User> login(String login, String password) throws ServiceException {
        Optional<User> optionalUser;
        try {
            optionalUser = UserDaoImpl.getInstance().findUser(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return optionalUser;
    }

    @Override
    public boolean signUp(Map<String, String> userData) throws ServiceException {
        boolean successfullySignedUp = true;
        String loginValue = userData.get(LOGIN_KEY);
        try {
            if (UserDaoImpl.getInstance().containsLogin(loginValue)) {
                userData.put(LOGIN_KEY, loginValue + ALREADY_EXISTS);
            } else if (UserDataValidator.areValidData(userData)) {
                String nameValue = userData.get(NAME_KEY);
                String surnameValue = userData.get(SURNAME_KEY);
                String emailValue = userData.get(EMAIL_KEY);
                User user = new User(loginValue, nameValue, surnameValue, emailValue);
                String passwordValue = userData.get(PASSWORD_KEY);
                String hashedPassword = Encryptor.encrypt(passwordValue);
                successfullySignedUp = UserDaoImpl.getInstance().addUser(user, hashedPassword);
            } else {
                successfullySignedUp = false;
            }
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        if (successfullySignedUp) {
            String emailValue = userData.get(EMAIL_KEY);
            String topic = "Welcome to GO!";
            String text = "Thank you for registering on our rental service. Wish you a great road!";
            sendMail(emailValue, topic, text);
        }
        return successfullySignedUp;
    }

    @Override
    public void sendMail(String addressee, String topic, String text) throws ServiceException {
        Properties properties = new Properties();
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream(MAIL_PROPERTIES_PATH);
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            logger.error("File of mail properties not found", e);
            throw new ServiceException("File of mail properties not found", e);
        }
        logger.debug(properties);
        MailSender sender = new MailSender(addressee, topic, text, properties);
        sender.send();
    }

}
