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

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Optional<User> login(String login, String password) {
        Optional<User> optionalUser = UserDaoImpl.getInstance().findUser(login, password);
        return optionalUser;
    }

    @Override
    public boolean signUp(String name, String surname, String email, String login, String password, String confirmedPassword) throws ServiceException {
        boolean successfullySignedUp = true;
        if (!UserDataValidator.isLogin(login)) {
            successfullySignedUp = false;
        } else {
            try {
                if (UserDaoImpl.getInstance().containsLogin(login)) {
                    successfullySignedUp = false;
                }
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        if (!UserDataValidator.isPassword(password)) {
            successfullySignedUp = false;
        }
        if (!UserDataValidator.isPassword(email)) {
            successfullySignedUp = false;
        }
        String hashedPassword = Encryptor.encrypt(password);
        User user = new User(login, name, surname, email);
        try {
            UserDaoImpl.getInstance().addUser(user, hashedPassword);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return successfullySignedUp;
    }

}
