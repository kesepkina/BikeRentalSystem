package com.epam.brs.command.impl;

import com.epam.brs.command.*;
import com.epam.brs.model.entity.Bicycle;
import com.epam.brs.model.entity.User;
import com.epam.brs.model.entity.UserRole;
import com.epam.brs.model.service.BicycleService;
import com.epam.brs.model.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DisplayBicyclesListCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private final BicycleService service;

    public DisplayBicyclesListCommand(BicycleService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String bikeType = request.getParameter(RequestParameter.BIKE_TYPE);
        List<Bicycle> bicycleList = null;
        try {
            bicycleList = service.findAll(bikeType);
        } catch (ServiceException e) {
            logger.error("Exception by getting list of bicycles", e);
            throw new CommandException("Exception by getting list of bicycles", e);
        }
        request.getSession().setAttribute("bicyclesList", bicycleList);
        User user = (User) request.getSession().getAttribute(SessionAttribute.USER);
        String page;
        if (user.getRole().equals(UserRole.ADMIN)) {
            page = PagePath.BICYCLES;
        } else {
            page = PagePath.CATALOG;
        }
        return page;
    }
}
