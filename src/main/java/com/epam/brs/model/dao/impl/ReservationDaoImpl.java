package com.epam.brs.model.dao.impl;

import com.epam.brs.model.dao.DaoException;
import com.epam.brs.model.dao.ReservationDao;
import com.epam.brs.model.entity.Reservation;
import com.epam.brs.model.entity.ReservationStatus;
import com.epam.brs.model.pool.ConnectionPool;
import org.intellij.lang.annotations.Language;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationDaoImpl implements ReservationDao {

    private static final ReservationDao instance = new ReservationDaoImpl();

    @Language("MySQL")
    private static final String FIND_ALL_SQL_QUERY = "SELECT id_reservation, id_user, id_bicycle, reserved_at, pick_up_time, return_time, counted_price, status FROM reservations";
    @Language("MySQL")
    private static final String FIND_BY_STATUS_SQL_QUERY = "SELECT id_reservation, id_user, id_bicycle, reserved_at, pick_up_time, return_time, counted_price, status FROM reservations WHERE status=?";
    @Language("MySQL")
    private static final String FIND_BY_ID_SQL_QUERY = "SELECT id_reservation, id_user, id_bicycle, reserved_at, pick_up_time, return_time, counted_price, status FROM reservations WHERE id_reservation=?";
    @Language("MySQL")
    private static final String ADD_RESERVATION_SQL_QUERY = "INSERT INTO reservations(id_user, id_bicycle, reserved_at, pick_up_time, return_time, counted_price, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

    private ReservationDaoImpl() {}

    public static ReservationDao getInstance() {
        return instance;
    }


    @Override
    public List<Reservation> findAll() throws DaoException {
        List<Reservation> reservationList;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_SQL_QUERY);
            reservationList = completeList(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return reservationList;
    }

    @Override
    public List<Reservation> findByStatus(ReservationStatus status) throws DaoException {
        List<Reservation> reservationList;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_STATUS_SQL_QUERY)) {
            statement.setString(1, status.toString());
            ResultSet resultSet = statement.executeQuery();
            reservationList = completeList(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return reservationList;
    }

    private List<Reservation> completeList(ResultSet reservations) throws SQLException {
        List<Reservation> reservationList = new ArrayList<>();
        while(reservations.next()) {
            int reservationId = reservations.getInt(1);
            int userId = reservations.getInt(2);
            int bicycleId = reservations.getInt(3);
            LocalDateTime reservedAt = (LocalDateTime) reservations.getObject(4);
            LocalDateTime pickUpTime = (LocalDateTime) reservations.getObject(5);
            LocalDateTime returnTime = (LocalDateTime) reservations.getObject(6);
            BigDecimal countedPrice = reservations.getBigDecimal(7);
            ReservationStatus status = ReservationStatus.valueOf(reservations.getString(8));
            Reservation reservation = new Reservation(reservationId, userId, bicycleId, reservedAt, pickUpTime, returnTime, countedPrice, status);
            reservationList.add(reservation);
        }
        return reservationList;
    }

    @Override
    public Optional<Reservation> find(Integer id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean delete(Reservation entity) {
        return false;
    }

    @Override
    public Reservation update(Reservation entity) {
        return null;
    }

    @Override
    public boolean addReservation(Reservation reservation) throws DaoException {
        boolean addedSuccessfully;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_RESERVATION_SQL_QUERY)) {
            int userId = reservation.getUserId();
            int bicycleId = reservation.getBicycleId();
            LocalDateTime reservedAt = reservation.getReservedAt();
            LocalDateTime pickUpTime = reservation.getPickUpTime();
            LocalDateTime returnTime = reservation.getReturnTime();
            BigDecimal countedPrice = reservation.getCountedPrice();
            ReservationStatus status = reservation.getStatus();
            statement.setInt(1, userId);
            statement.setInt(2, bicycleId);
            statement.setObject(3, reservedAt.toString().replace('T', ' '));
            statement.setObject(4, pickUpTime.toString().replace('T', ' '));
            statement.setObject(5, returnTime.toString().replace('T', ' '));
            statement.setBigDecimal(6, countedPrice);
            statement.setString(7, status.toString());
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                addedSuccessfully = true;
                logger.info("Reservation added successfully");
            } else {
                logger.error("Reservation {} wasn't added", reservation);
                addedSuccessfully = false;
            }
        } catch (SQLException e) {
            logger.error("Exception while adding new reservation", e);
            throw new DaoException("Exception while adding new reservation", e);
        }
        return addedSuccessfully;
    }

    @Override
    public boolean changeReservationStatus(int reservationId, ReservationStatus newReservationStatus) {
        return false;
    }

}
