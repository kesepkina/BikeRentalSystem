package com.epam.brs.command.impl;

import com.epam.brs.command.*;
import com.epam.brs.model.entity.Reservation;
import com.epam.brs.model.entity.User;
import com.epam.brs.model.service.ServiceException;
import com.epam.brs.model.service.impl.ReservationServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class DisplayUserOrdersCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private final ReservationServiceImpl service;

    public DisplayUserOrdersCommand(ReservationServiceImpl service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException, IOException, ServletException {
        List<Reservation> reservationList;
        User user = (User)request.getSession().getAttribute(SessionAttribute.USER);
        int userId = user.getUserId();
        try {
            reservationList = service.findByUserId(userId);
        } catch (ServiceException e) {
            logger.error("Exception by getting list of bicycles", e);
            throw new CommandException("Exception by getting list of bicycles", e);
        }
        request.getSession().setAttribute("orders", reservationList);
        return PagePath.PROFILE;
    }
}
