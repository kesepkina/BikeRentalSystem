package com.epam.brs.command.impl;

import com.epam.brs.command.Command;
import com.epam.brs.command.CommandException;
import com.epam.brs.command.PagePath;
import com.epam.brs.command.RequestParameter;
import com.epam.brs.model.entity.Reservation;
import com.epam.brs.model.service.ReservationService;
import com.epam.brs.model.service.ServiceException;
import com.epam.brs.model.service.impl.ReservationServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeleteReservationCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private final ReservationService service;

    public DeleteReservationCommand(ReservationServiceImpl service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        int orderId = Integer.parseInt(request.getParameter(RequestParameter.ORDER_ID));
        boolean deleted;
        try {
            deleted = service.delete(orderId);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        if (deleted) {
            request.setAttribute("success", "yes!");
            List<Reservation> reservationList;
            try {
                reservationList = service.findAllReservations();
            } catch (ServiceException e) {
                logger.error("Exception by getting list of orders", e);
                throw new CommandException("Exception by getting list of orders", e);
            }
            request.getSession().setAttribute("orders", reservationList);
        } else {
            request.setAttribute("success", "no");
        }
        return PagePath.ORDERS;
    }
}
