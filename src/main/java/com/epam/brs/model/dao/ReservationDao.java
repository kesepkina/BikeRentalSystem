package com.epam.brs.model.dao;

import com.epam.brs.model.entity.Reservation;
import com.epam.brs.model.entity.ReservationStatus;

import java.util.List;

public interface ReservationDao extends BaseDao<Integer, Reservation> {

    boolean addReservation(Reservation reservation) throws DaoException;

    boolean changeReservationStatus(int reservationId, ReservationStatus newReservationStatus);

    List<Reservation> findByStatus(ReservationStatus status) throws DaoException;
}
