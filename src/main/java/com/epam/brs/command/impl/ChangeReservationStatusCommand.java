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

public class ChangeReservationStatusCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private final ReservationService service;

    public ChangeReservationStatusCommand(ReservationServiceImpl service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        int orderId = Integer.parseInt(request.getParameter(RequestParameter.ORDER_ID));
        String newStatus = request.getParameter(RequestParameter.ORDER_STATUS);
        boolean updated;
        try {
            updated = service.updateReservationStatus(orderId, newStatus);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        if (updated) {
            request.setAttribute("successUpd", "yes!");
            List<Reservation> reservationList;
            try {
                reservationList = service.findAllReservations();
            } catch (ServiceException e) {
                logger.error("Exception by getting list of orders", e);
                throw new CommandException("Exception by getting list of orders", e);
            }
            request.getSession().setAttribute("orders", reservationList);
        } else {
            request.setAttribute("successUpd", "no");
        }
        return PagePath.ORDERS;
    }
}
