package com.epam.brs.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.StringJoiner;

public class Reservation extends Entity{

    private int reservationId;
    private int userId;
    private int bicycleId;
    private LocalDateTime reservedAt;
    private LocalDateTime pickUpTime;
    private LocalDateTime returnTime;
    private BigDecimal countedPrice;
    private ReservationStatus status;

    public Reservation(int reservationId, int userId, int bicycleId, LocalDateTime reservedAt, LocalDateTime pickUpTime, LocalDateTime returnTime, BigDecimal countedPrice, ReservationStatus status) {
        this.reservationId = reservationId;
        this.userId = userId;
        this.bicycleId = bicycleId;
        this.reservedAt = reservedAt;
        this.pickUpTime = pickUpTime;
        this.returnTime = returnTime;
        this.countedPrice = countedPrice;
        this.status = status;
    }

    public Reservation(int userId, int bicycleId, LocalDateTime reservedAt, LocalDateTime pickUpTime, LocalDateTime returnTime, BigDecimal countedPrice, ReservationStatus status) {
        this.userId = userId;
        this.bicycleId = bicycleId;
        this.reservedAt = reservedAt;
        this.pickUpTime = pickUpTime;
        this.returnTime = returnTime;
        this.countedPrice = countedPrice;
        this.status = status;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBicycleId() {
        return bicycleId;
    }

    public void setBicycleId(int bicycleId) {
        this.bicycleId = bicycleId;
    }

    public LocalDateTime getReservedAt() {
        return reservedAt;
    }

    public void setReservedAt(LocalDateTime reservedAt) {
        this.reservedAt = reservedAt;
    }

    public LocalDateTime getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(LocalDateTime pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public LocalDateTime getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(LocalDateTime returnTime) {
        this.returnTime = returnTime;
    }

    public BigDecimal getCountedPrice() {
        return countedPrice;
    }

    public void setCountedPrice(BigDecimal countedPrice) {
        this.countedPrice = countedPrice;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reservation that = (Reservation) o;

        if (reservationId != that.reservationId) return false;
        if (userId != that.userId) return false;
        if (bicycleId != that.bicycleId) return false;
        if (reservedAt != null ? !reservedAt.equals(that.reservedAt) : that.reservedAt != null) return false;
        if (pickUpTime != null ? !pickUpTime.equals(that.pickUpTime) : that.pickUpTime != null) return false;
        if (returnTime != null ? !returnTime.equals(that.returnTime) : that.returnTime != null) return false;
        if (countedPrice != null ? !countedPrice.equals(that.countedPrice) : that.countedPrice != null) return false;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        int result = reservationId;
        result = 31 * result + userId;
        result = 31 * result + bicycleId;
        result = 31 * result + (reservedAt != null ? reservedAt.hashCode() : 0);
        result = 31 * result + (pickUpTime != null ? pickUpTime.hashCode() : 0);
        result = 31 * result + (returnTime != null ? returnTime.hashCode() : 0);
        result = 31 * result + (countedPrice != null ? countedPrice.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Reservation.class.getSimpleName() + "[", "]")
                .add("reservationId=" + reservationId)
                .add("userId=" + userId)
                .add("bicycleId=" + bicycleId)
                .add("reservedAt=" + reservedAt)
                .add("pickUpTime=" + pickUpTime)
                .add("returnTime=" + returnTime)
                .add("countedPrice=" + countedPrice)
                .add("status=" + status)
                .toString();
    }
}
