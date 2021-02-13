package com.epam.brs.command.impl;

import com.epam.brs.command.Command;
import com.epam.brs.command.RequestParameter;
import com.epam.brs.model.service.ServiceException;
import com.epam.brs.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

import static com.epam.brs.command.PagePath.*;

public class SignUpCommand implements Command {

    private static final String PARAM_NAME = "name";

    private final UserServiceImpl service;

    public SignUpCommand(UserServiceImpl service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String nameValue = request.getParameter(RequestParameter.NAME.getValue());
        String surnameValue = request.getParameter(RequestParameter.SURNAME.getValue());
        String emailValue = request.getParameter(RequestParameter.EMAIL.getValue());
        String loginValue = request.getParameter(RequestParameter.LOGIN.getValue());
        String passValue = request.getParameter(RequestParameter.PASSWORD.getValue());
        String passConfirmValue = request.getParameter(RequestParameter.PASSWORD_CONFIRMING.getValue());
        boolean dataAreCorrect = false;
        try {
            dataAreCorrect = service.signUp(nameValue, surnameValue, emailValue, loginValue, passValue, passConfirmValue);
        } catch (ServiceException e) {
            //TODO
            e.printStackTrace();
        }

        if (dataAreCorrect) {
            page = SIGNUP;
        } else {
            request.setAttribute(PARAM_NAME, nameValue);
            page = LOGIN;
        }
        return page;
    }
}
