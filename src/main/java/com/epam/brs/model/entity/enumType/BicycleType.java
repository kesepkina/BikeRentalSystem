package com.epam.brs.model.entity.enumType;

public enum BicycleType {

    CITY_BIKE ("City bike"),
    MOUNTAIN_BIKE ("Mountain bike"),
    EBIKE ("E-Bike"),
    CHILDREN_BIKE ("Children bike"),
    RACING_BIKE ("Racing bike"),
    BMX ("BMX");

    private final String value;

    BicycleType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
