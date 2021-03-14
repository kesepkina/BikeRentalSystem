package com.epam.brs.command.impl;

import com.epam.brs.command.Command;
import com.epam.brs.command.RequestParameter;
import com.epam.brs.command.UserDataMapKeyword;
import com.epam.brs.model.service.ServiceException;
import com.epam.brs.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static com.epam.brs.command.PagePath.*;
import static com.epam.brs.command.UserDataMapKeyword.*;

public class SignUpCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private final UserServiceImpl service;

    public SignUpCommand(UserServiceImpl service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String nameValue = request.getParameter(RequestParameter.NAME);
        String surnameValue = request.getParameter(RequestParameter.SURNAME);
        String emailValue = request.getParameter(RequestParameter.EMAIL);
        String loginValue = request.getParameter(RequestParameter.LOGIN);
        String passValue = request.getParameter(RequestParameter.PASSWORD);
        String passConfirmValue = request.getParameter(RequestParameter.PASSWORD_CONFIRMING);
        Map<String, String> userData = new HashMap<>();
        userData.put(NAME_KEY, nameValue);
        userData.put(SURNAME_KEY, surnameValue);
        userData.put(EMAIL_KEY, emailValue);
        userData.put(LOGIN_KEY, loginValue);
        userData.put(PASSWORD_KEY, passValue);
        userData.put(CONFIRMING_PASSWORD_KEY, passConfirmValue);
        boolean signedUpSuccessfully;
        try {
            signedUpSuccessfully = service.signUp(userData);
            if (signedUpSuccessfully) {
                page = LOGIN;
            } else {
                emailValue = userData.get(EMAIL_KEY);
                loginValue = userData.get(LOGIN_KEY);
                passValue = userData.get(PASSWORD_KEY);
                passConfirmValue = userData.get(CONFIRMING_PASSWORD_KEY);
                if (emailValue.contains(INCORRECT_VALUE)) {
                    // TODO: include properties
                    request.setAttribute("errorEmailMessage", "Incorrect email");
                }
                if (loginValue.contains(INCORRECT_VALUE)) {
                    // TODO: include properties
                    request.setAttribute("errorLoginMessage", "Incorrect login. It must include only letters, ciphers, characters \".\", \"_\" and have from 5 to 20 characters");
                } else if (loginValue.contains(UserDataMapKeyword.ALREADY_EXISTS)) {
                    request.setAttribute("errorLoginMessage", "This login already exists.");
                }
                if (passValue.contains(INCORRECT_VALUE)) {
                    // TODO: include properties
                    request.setAttribute("errorPasswordMessage", "Incorrect password. It must include at least one letter in upper and in lower case, at least one cipher, at least one special character (\"@\", \"#\". \"$\", \"%\", \"^\", \"&\", \"(\" or \")\", no spaces and have from 8 to 20 characters");
                }
                if (passConfirmValue.contains(DOESNT_MATCH)) {
                    // TODO: include properties
                    request.setAttribute("errorPasswordConfirmingMessage", "Doesn't match");
                }
                page = SIGNUP;
            }
        } catch (ServiceException e) {
            logger.error("Exception while signing up", e);
            page = ERROR500;
        }
        return page;
    }
}
