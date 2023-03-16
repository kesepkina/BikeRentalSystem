package com.epam.brs.model.dao;

import com.epam.brs.model.entity.Reservation;
import com.epam.brs.model.entity.enumType.ReservationStatus;

import java.util.List;

public interface ReservationDao extends BaseDao<Integer, Reservation> {

    List<Reservation> findByUserId(int userId) throws DaoException;

    List<Reservation> findByBicycleId(int userId) throws DaoException;

    boolean downloadTableAsJSON() throws DaoException;

    boolean addReservation(Reservation reservation) throws DaoException;

    boolean changeReservationStatus(int reservationId, ReservationStatus newReservationStatus) throws DaoException;

    List<Reservation> findByStatus(ReservationStatus status) throws DaoException;
}
