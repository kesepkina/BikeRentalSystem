package com.epam.brs.command.impl;

import com.epam.brs.command.*;
import com.epam.brs.model.entity.User;
import com.epam.brs.model.entity.enumType.UserRole;
import com.epam.brs.model.service.ServiceException;
import com.epam.brs.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import java.util.Optional;

import static com.epam.brs.command.PagePath.*;

public class LogInCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private final UserServiceImpl service;

    public LogInCommand(UserServiceImpl service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
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
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.isBlocked()) {
                request.setAttribute("userIsBlocked", "true");
                page = LOGIN;
            } else {
                request.getSession().setAttribute(SessionAttribute.USER, user);
                request.getSession().setAttribute(SessionAttribute.USER_ROLE, user.getRole().toString());
                if (user.getRole().equals(UserRole.ADMIN)) {
                    page = ADMIN_MAIN;
                } else {
                    page = MAIN;
                }
            }
        } else {
            request.setAttribute("errorUserMessage", "Incorrect login or password");
            page = LOGIN;
        }
        return page;
    }
}
