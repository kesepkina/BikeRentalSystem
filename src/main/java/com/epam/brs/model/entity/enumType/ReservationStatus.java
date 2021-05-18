package com.epam.brs.model.entity.enumType;

public enum ReservationStatus {

    TO_CONFIRM ("To confirm"),
    PENDING ("Pending"),
    CONFIRMED ("Confirmed"),
    REFUSED ("Refused"),
    CANCELLED ("Cancelled"),
    CANCELLED_BY_TENOR ("Cancelled by tenor"),
    CANCELLED_BY_LANDLORD ("Cancelled by landlord"),
    EXPIRED ("Expired");

    private final String value;

    ReservationStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
