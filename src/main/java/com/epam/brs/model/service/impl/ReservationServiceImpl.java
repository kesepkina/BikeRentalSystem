package com.epam.brs.model.service.impl;

import com.epam.brs.model.dao.DaoException;
import com.epam.brs.model.dao.impl.PriceListDaoImpl;
import com.epam.brs.model.dao.impl.ReservationDaoImpl;
import com.epam.brs.model.dao.impl.UserDaoImpl;
import com.epam.brs.model.entity.*;
import com.epam.brs.model.entity.enumType.ReservationStatus;
import com.epam.brs.model.service.BookingException;
import com.epam.brs.model.service.ReservationService;
import com.epam.brs.model.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class ReservationServiceImpl implements ReservationService {

    private static final Logger log = LogManager.getLogger();

    @Override
    public List<Reservation> findByUserId(int userId) throws ServiceException {
        List<Reservation> reservationList;
        try {
            reservationList = ReservationDaoImpl.getInstance().findByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return reservationList;
    }

    @Override
    public List<Reservation> findByBicycleId(int bicycleId) throws ServiceException {
        List<Reservation> reservationList;
        try {
            reservationList = ReservationDaoImpl.getInstance().findByBicycleId(bicycleId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return reservationList;
    }

    @Override
    public List<Map<String, String>> getReservationPeriods(int bicycleId) throws ServiceException {
        List<Reservation> reservationList;
        try {
            reservationList = ReservationDaoImpl.getInstance().findByBicycleId(bicycleId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        List<Map<String,String>> dictonaries = new ArrayList<>();
        DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        for (Reservation reservation : reservationList) {
            Map<String, String> dictonary = new HashMap<>();
            dictonary.put("start", reservation.getPickUpTime().format(customFormat));
            dictonary.put("end", reservation.getReturnTime().format(customFormat));
            dictonaries.add(dictonary);
        }
        return dictonaries;
    }

    @Override
    public boolean downloadTableAsJSON() throws ServiceException {
        boolean downloaded;
        try {
            downloaded = ReservationDaoImpl.getInstance().downloadTableAsJSON();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return downloaded;
    }

    @Override
    public boolean updateReservationStatus(int reservationId, String newStatus) throws ServiceException {
        boolean updatedSuccessfully;
        ReservationStatus newReservationStatus = ReservationStatus.valueOf(newStatus);
        try {
            updatedSuccessfully = ReservationDaoImpl.getInstance().changeReservationStatus(reservationId, newReservationStatus);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return updatedSuccessfully;
    }

    @Override
    public boolean delete(int reservationId) throws ServiceException {
        boolean deletedSuccessfully;
        try {
            deletedSuccessfully = ReservationDaoImpl.getInstance().delete(reservationId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return deletedSuccessfully;
    }

    @Override
    public BigDecimal addReservation(int userId, int bicycleId, String pickUpRange, int priceListId) throws ServiceException, BookingException {
        boolean successfullyAdded;
        BigDecimal pricePerUnit;
        LocalDateTime returnTime;
        LocalDateTime pickUpTime;
        BigDecimal calculatedCost = BigDecimal.valueOf(0.0);
        try {
            Optional<PriceList> optionalPriceList = PriceListDaoImpl.getInstance().find(priceListId);
            if (optionalPriceList.isPresent()) {
                String[] dateRange = pickUpRange.split(" - ");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy H:mm");
                pickUpTime = LocalDateTime.parse(dateRange[0], formatter);
                returnTime = LocalDateTime.parse(dateRange[1], formatter);

                if (conflictsWithExistingReservations(bicycleId, pickUpTime, returnTime)) {
                    throw new BookingException("Requested rent period conflicts with already existing reservations. " +
                            "Please input another one.");
                }
                LocalDateTime tempDateTime = LocalDateTime.from( pickUpTime );

                long weeks = tempDateTime.until( returnTime, ChronoUnit.WEEKS );
                tempDateTime = tempDateTime.plusWeeks( weeks );
                if (weeks > 0) {
                    pricePerUnit = optionalPriceList.get().getPricePerWeek();
                    calculatedCost = calculatedCost.add(pricePerUnit.multiply(BigDecimal.valueOf(weeks)));
                }

                long days = tempDateTime.until( returnTime, ChronoUnit.DAYS );
                tempDateTime = tempDateTime.plusDays( days );
                if (days > 0) {
                    pricePerUnit = optionalPriceList.get().getPricePerDay();
                    calculatedCost = calculatedCost.add(pricePerUnit.multiply(BigDecimal.valueOf(days)));
                }

                long hours = tempDateTime.until( returnTime, ChronoUnit.HOURS );
                if (hours > 0) {
                    pricePerUnit = optionalPriceList.get().getPricePerHour();
                    calculatedCost = calculatedCost.add(pricePerUnit.multiply(BigDecimal.valueOf(hours)));
                }
            } else {
                throw new ServiceException("Couldn't find price list");
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        LocalDateTime currentTime = LocalDateTime.now();
        Reservation reservation = new Reservation(userId, bicycleId, currentTime, pickUpTime, returnTime, calculatedCost, ReservationStatus.TO_CONFIRM);
        try {
            ReservationDaoImpl.getInstance().addReservation(reservation);
        } catch (DaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }
        return calculatedCost;
    }

    private boolean conflictsWithExistingReservations(int bicycleId, LocalDateTime pickUpTime, LocalDateTime returnTime) throws ServiceException {
        List<Reservation> reservationList= findByBicycleId(bicycleId);
        boolean overlaps = false;
        for (Reservation reservation : reservationList) {
            if (!(pickUpTime.isAfter(reservation.getReturnTime()) || returnTime.isBefore(reservation.getPickUpTime()))) {
                overlaps = true;
                break;
            }
        }
        return overlaps;
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

    @Override
    public boolean downloadRentContract(int reservationId) {
        try {
            Reservation reservation = ReservationDaoImpl.getInstance().find(reservationId).orElseThrow();
            User user = UserDaoImpl.getInstance().find(reservation.getUserId()).orElseThrow();
            
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return false;
    }
}
