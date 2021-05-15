package com.epam.brs.command.impl;

import com.epam.brs.command.Command;
import com.epam.brs.command.CommandException;
import com.epam.brs.command.PagePath;
import com.epam.brs.model.entity.User;
import com.epam.brs.model.service.ReservationService;
import com.epam.brs.model.service.ServiceException;
import com.epam.brs.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DisplayUsersListCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private final UserService service;

    public DisplayUsersListCommand(UserService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        List<User> userList;
        try {
            userList = service.findAll();
        } catch (ServiceException e) {
            logger.error("Exception by getting list of users", e);
            throw new CommandException("Exception by getting list of users", e);
        }
        request.getSession().setAttribute("users", userList);
        return PagePath.USERS;
    }
}
