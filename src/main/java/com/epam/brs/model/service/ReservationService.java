package com.epam.brs.model.service;

import com.epam.brs.model.entity.Reservation;
import com.epam.brs.model.entity.ReservationStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {

    boolean addReservation(int userId, int bicycleId, LocalDateTime pickUpTime,int timeLength, String timeFormat, int priceListId) throws ServiceException;

    List<Reservation> findAllReservations() throws ServiceException;

    List<Reservation> findReservationsByStatus(ReservationStatus status);
}
