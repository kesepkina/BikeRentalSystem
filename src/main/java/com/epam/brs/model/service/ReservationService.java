package com.epam.brs.model.service;

import com.epam.brs.model.entity.Reservation;
import com.epam.brs.model.entity.enumType.ReservationStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {

    List<Reservation> findByUserId(int userId) throws ServiceException;

    boolean downloadTableAsJSON() throws ServiceException;

    boolean updateReservationStatus(int reservationId, String newStatus) throws ServiceException;

    boolean delete(int reservationId) throws ServiceException;

    boolean addReservation(int userId, int bicycleId, LocalDateTime pickUpTime,int timeLength, String timeFormat, int priceListId) throws ServiceException;

    List<Reservation> findAllReservations() throws ServiceException;

    List<Reservation> findReservationsByStatus(ReservationStatus status);
}
