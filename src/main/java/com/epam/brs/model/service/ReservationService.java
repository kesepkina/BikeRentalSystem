package com.epam.brs.model.service;

import com.epam.brs.model.entity.Reservation;
import com.epam.brs.model.entity.enumType.ReservationStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ReservationService {

    List<Reservation> findByUserId(int userId) throws ServiceException;

    List<Reservation> findByBicycleId(int bicycleId) throws ServiceException;

    List<Map<String, String>> getReservationPeriods(int bicycleId) throws ServiceException;

    boolean downloadTableAsJSON() throws ServiceException;

    boolean updateReservationStatus(int reservationId, String newStatus) throws ServiceException;

    boolean delete(int reservationId) throws ServiceException;

    BigDecimal addReservation(int userId, int bicycleId, String pickUpRange, int priceListId) throws ServiceException, BookingException;

    List<Reservation> findAllReservations() throws ServiceException;

    List<Reservation> findReservationsByStatus(ReservationStatus status);

    boolean downloadRentContract(int reservationId);
}
