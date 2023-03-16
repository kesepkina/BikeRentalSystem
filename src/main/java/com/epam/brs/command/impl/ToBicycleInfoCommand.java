package com.epam.brs.command.impl;

import com.epam.brs.command.Command;
import com.epam.brs.command.CommandException;
import com.epam.brs.command.PagePath;
import com.epam.brs.command.SessionAttribute;
import com.epam.brs.model.entity.Bicycle;
import com.epam.brs.model.entity.PriceList;
import com.epam.brs.model.service.ServiceException;
import com.epam.brs.model.service.impl.BicycleServiceImpl;
import com.epam.brs.model.service.impl.PriceListServiceImpl;
import com.epam.brs.model.service.impl.ReservationServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ToBicycleInfoCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private final BicycleServiceImpl bicycleService;
    private final PriceListServiceImpl priceListService;
    private final ReservationServiceImpl reservationService;

    public ToBicycleInfoCommand(
            BicycleServiceImpl bicycleService,
            PriceListServiceImpl priceListService,
            ReservationServiceImpl reservationService
    ) {
        this.bicycleService = bicycleService;
        this.priceListService = priceListService;
        this.reservationService = reservationService;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        int id = Integer.parseInt(request.getParameter("id"));
        Optional<Bicycle> optionalBicycle;
        try {
            optionalBicycle = bicycleService.findBicycle(id);
        } catch (ServiceException e) {
            logger.error("Exception by getting bicycle with id {}", id, e);
            throw new CommandException("Exception by getting bicycle with id " + id, e);
        }
        String page;
        if (optionalBicycle.isPresent()) {
            Bicycle bicycle = optionalBicycle.get();
            request.getSession().setAttribute(SessionAttribute.CHOSEN_BICYCLE, bicycle);
            int priceListId = bicycle.getPriceListId();
            try {
                Optional<PriceList> optionalPriceList = priceListService.findById(priceListId);
                optionalPriceList.ifPresent(priceList -> request.getSession().setAttribute(SessionAttribute.PRICE_LIST, priceList));
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
            int bicycleId = bicycle.getBicycleId();
            try {
                List<Map<String, String>> reservationList = reservationService.getReservationPeriods(bicycleId);
                request.getSession().setAttribute(SessionAttribute.RESERVED_PERIODS, reservationList);
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
            page = PagePath.BICYCLE_INFO;
        } else {
            logger.error("Bicycle with id {} wasn't found in database", id);
            page = PagePath.BICYCLE_NOT_FOUND;
        }
        return page;
    }
}
