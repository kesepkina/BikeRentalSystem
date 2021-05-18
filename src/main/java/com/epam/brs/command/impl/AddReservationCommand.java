package com.epam.brs.command.impl;

import com.epam.brs.command.Command;
import com.epam.brs.command.CommandException;
import com.epam.brs.command.RequestParameter;
import com.epam.brs.command.SessionAttribute;
import com.epam.brs.model.entity.Bicycle;
import com.epam.brs.model.entity.User;
import com.epam.brs.model.service.ReservationService;
import com.epam.brs.model.service.ServiceException;
import com.epam.brs.model.service.impl.ReservationServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

import static com.epam.brs.command.PagePath.*;

public class AddReservationCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private final ReservationService service;

    public AddReservationCommand(ReservationServiceImpl service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        LocalDateTime pickUpTime = LocalDateTime.parse(request.getParameter(RequestParameter.PICK_UP_DATE));
        int length = Integer.parseInt(request.getParameter(RequestParameter.AMOUNT));
        String timeFormat = request.getParameter(RequestParameter.TIME_FORMAT);
        Bicycle chosenBicycle = (Bicycle) request.getSession().getAttribute(SessionAttribute.CHOSEN_BICYCLE);
        int bicycleId = chosenBicycle.getBicycleId();
        int priceListId = chosenBicycle.getPriceListId();
        User user = (User) request.getSession().getAttribute(SessionAttribute.USER);
        int userId = user.getUserId();
        try {
            service.addReservation(userId, bicycleId, pickUpTime, length, timeFormat, priceListId);
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException("Reservation wasn't added", e);
        }
        request.setAttribute("infoModal", "Success");
        return BICYCLE_INFO;
    }
}
