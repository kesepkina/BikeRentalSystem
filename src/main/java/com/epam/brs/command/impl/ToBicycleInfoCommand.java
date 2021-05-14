package com.epam.brs.command.impl;

import com.epam.brs.command.Command;
import com.epam.brs.command.CommandException;
import com.epam.brs.command.PagePath;
import com.epam.brs.model.entity.Bicycle;
import com.epam.brs.model.service.ServiceException;
import com.epam.brs.model.service.impl.BicycleServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ToBicycleInfoCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private final BicycleServiceImpl service;

    public ToBicycleInfoCommand(BicycleServiceImpl service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        int id = Integer.parseInt(request.getParameter("id"));
        Optional<Bicycle> optionalBicycle;
        try {
            optionalBicycle = service.findBicycle(id);
        } catch (ServiceException e) {
            logger.error("Exception by getting bicycle with id {}", id, e);
            throw new CommandException("Exception by getting bicycle with id " + id, e);
        }
        String page;
        if (optionalBicycle.isPresent()) {
            Bicycle bicycle = optionalBicycle.get();
            request.getSession().setAttribute("chosenBicycle", bicycle);
            page = PagePath.BICYCLE_INFO;
        } else {
            logger.error("Bicycle with id {} wasn't found in database", id);
            page = PagePath.BICYCLE_NOT_FOUND;
        }
        return page;
    }
}
