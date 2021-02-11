package com.epam.brs.controller.command.impl;

import com.epam.brs.controller.command.Command;
import com.epam.brs.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;

import static com.epam.brs.controller.command.PagePath.*;

public class LogInCommand implements Command {

    private static final String PARAM_NAME = "user";
    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";

    private final UserServiceImpl service;

    public LogInCommand(UserServiceImpl service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String login = request.getParameter(PARAM_LOGIN);
        String passValue = request.getParameter(PARAM_PASSWORD);
        boolean dataAreCorrect = true;
        if (!service.isLogin(login)) {
            request.setAttribute("errorLoginMessage", "Login isn't correct. It must include only letters, ciphers, characters \".\", \"_\" and have at least 5 characters.");
            dataAreCorrect = false;
        }
        if (!service.isPassword(passValue)) {
            request.setAttribute("errorPasswordMessage", "Password isn't correct. It must include at least one letter in upper and in lower case, at least one cipher, at least one special character (\"@\", \"#\". \"$\", \"%\", \"^\", \"&\", \"(\" or \")\", no spaces and have from 8 to 20 characters.");
            dataAreCorrect = false;
        }
        if (service.checkLogin(login)) {
            if (!service.checkUser(login, passValue)) {
                request.setAttribute("errorUserMessage", "Incorrect password");
                dataAreCorrect = false;
            }
        } else {
            request.setAttribute("errorUserMessage", "User with inputted login doesn't exist.");
            dataAreCorrect = false;
        }
        if (dataAreCorrect) {
            try {
                request.setCharacterEncoding("utf8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            request.setAttribute(PARAM_NAME, service.getName(login));
            page = MAIN;
        } else {
            page = SIGNUP;
        }
        return page;
    }
}
