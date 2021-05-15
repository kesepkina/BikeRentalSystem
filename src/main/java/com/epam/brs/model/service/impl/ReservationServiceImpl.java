package com.epam.brs.model.service.impl;

import com.epam.brs.model.dao.DaoException;
import com.epam.brs.model.dao.impl.BicycleDaoImpl;
import com.epam.brs.model.dao.impl.PriceListDaoImpl;
import com.epam.brs.model.dao.impl.ReservationDaoImpl;
import com.epam.brs.model.dao.impl.UserDaoImpl;
import com.epam.brs.model.entity.*;
import com.epam.brs.model.service.ReservationService;
import com.epam.brs.model.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ReservationServiceImpl implements ReservationService {

    private static final Logger log = LogManager.getLogger();

    @Override
    public boolean addReservation(int userId, int bicycleId, LocalDateTime pickUpTime, int timeLength, String timeFormat, int priceListId) throws ServiceException {
        boolean successfullyAdded;
        BigDecimal pricePerUnit;
        LocalDateTime returnTime;
        try {
            Optional<PriceList> optionalPriceList = PriceListDaoImpl.getInstance().find(priceListId);
            if (optionalPriceList.isPresent()) {
                if (timeFormat.equals("hours")) {
                    pricePerUnit = optionalPriceList.get().getPricePerHour();
                    returnTime = pickUpTime.plusHours(timeLength);
                } else if (timeFormat.equals("days")) {
                    pricePerUnit = optionalPriceList.get().getPricePerDay();
                    returnTime = pickUpTime.plusDays(timeLength);
                } else {
                    pricePerUnit = optionalPriceList.get().getPricePerWeek();
                    returnTime = pickUpTime.plusWeeks(timeLength);
                }
            } else {
                throw new ServiceException("Couldn't find price list");
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        BigDecimal calculatedCost = pricePerUnit.multiply(BigDecimal.valueOf(timeLength));
        LocalDateTime currentTime = LocalDateTime.now();
        Reservation reservation = new Reservation(userId, bicycleId, currentTime, pickUpTime, returnTime, calculatedCost, ReservationStatus.TO_CONFIRM);
        try {
            successfullyAdded = ReservationDaoImpl.getInstance().addReservation(reservation);
        } catch (DaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }
        return successfullyAdded;
    }

    @Override
    public List<Reservation> findAllReservations() throws ServiceException {
        List<Reservation> reservationList;
        try {
            reservationList = ReservationDaoImpl.getInstance().findAll();
            for (Reservation reservation:
                 reservationList) {
                int userId = reservation.getUserId();
                Optional<String> optEmail = UserDaoImpl.getInstance().findUserEmail(userId);
                if (optEmail.isPresent()) {
                    reservation.setUserEmail(optEmail.get());
                } else {
                    reservation.setUserEmail("-");
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return reservationList;
    }

    @Override
    public List<Reservation> findReservationsByStatus(ReservationStatus status) {
        return null;
    }
}
