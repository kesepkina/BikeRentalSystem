package com.epam.brs.command.impl;

import com.epam.brs.command.Command;
import com.epam.brs.command.RequestParameter;
import com.epam.brs.command.SessionAttribute;
import com.epam.brs.model.entity.User;
import com.epam.brs.model.entity.UserRole;
import com.epam.brs.model.service.ServiceException;
import com.epam.brs.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.Properties;

import static com.epam.brs.command.PagePath.*;

public class LogInCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private static final String PARAM_NAME = "user";
    private final UserServiceImpl service;

    public LogInCommand(UserServiceImpl service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String loginValue = request.getParameter(RequestParameter.LOGIN.getValue());
        String passValue = request.getParameter(RequestParameter.PASSWORD.getValue());
        Optional<User> optionalUser= service.login(loginValue, passValue);
        User user;
        if(optionalUser.isPresent()) {
            user = optionalUser.get();
            request.getSession().setAttribute(SessionAttribute.LOGIN, loginValue);
            String name = user.getName();
            request.getSession().setAttribute(SessionAttribute.NAME, name);
            String surname = user.getSurname();
            request.getSession().setAttribute(SessionAttribute.SURNAME, surname);
            String email = user.getEmail();
            request.getSession().setAttribute(SessionAttribute.EMAIL, email);
            UserRole role = user.getRole();
            request.getSession().setAttribute(SessionAttribute.USER_ROLE, role.toString());
            page = MAIN;
            try {
                // TODO: delete if useless
                request.setCharacterEncoding("utf8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            request.setAttribute("errorUserMessage", "Incorrect login or password");
            page = SIGNUP;
        }
        return page;
    }
}
