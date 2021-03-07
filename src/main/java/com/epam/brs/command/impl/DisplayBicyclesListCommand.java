package com.epam.brs.command.impl;

import com.epam.brs.command.Command;
import com.epam.brs.command.CommandException;
import com.epam.brs.command.PagePath;
import com.epam.brs.model.entity.Bicycle;
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
        String page = PagePath.CATALOG;
        List<Bicycle> bicycleList = null;
        try {
            bicycleList = service.findAll();
        } catch (ServiceException e) {
            logger.error("Exception by getting list of bicycles", e);
            throw new CommandException("Exception by getting list of bicycles", e);
        }
        request.setAttribute("bicyclesList", bicycleList);
        return page;
    }
}
