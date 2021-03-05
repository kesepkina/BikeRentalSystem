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

import static com.epam.brs.command.PagePath.*;

public class LogInCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private final UserServiceImpl service;

    public LogInCommand(UserServiceImpl service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String loginValue = request.getParameter(RequestParameter.LOGIN);
        String passValue = request.getParameter(RequestParameter.PASSWORD);
        Optional<User> optionalUser = Optional.empty();
        try {
            optionalUser = service.login(loginValue, passValue);
        } catch (ServiceException e) {
            logger.error("Exception while logging in", e);
            request.setAttribute("ErrorMessage", "Exception while logging in: " + e.getMessage());
        }
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
            page = LOGIN;
        }
        return page;
    }
}
