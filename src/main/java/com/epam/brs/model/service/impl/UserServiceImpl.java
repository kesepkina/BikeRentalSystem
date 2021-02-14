package com.epam.brs.model.service.impl;

import com.epam.brs.model.dao.DaoException;
import com.epam.brs.model.dao.impl.UserDaoImpl;
import com.epam.brs.model.entity.User;
import com.epam.brs.model.service.ServiceException;
import com.epam.brs.model.service.UserService;
import com.epam.brs.util.Encryptor;
import com.epam.brs.validator.UserDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger();
    private static final String LOGIN_KEY = "login";
    private static final String EMAIL_KEY = "email";
    private static final String PASSWORD_KEY = "password";
    private static final String NAME_KEY = "name";
    private static final String SURNAME_KEY = "surname";
    private static final String ALREADY_EXISTS = " already exists";

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
        return successfullySignedUp;
    }

}
