package com.epam.brs.command;

public enum RequestParameter {

    NAME ("name"),
    SURNAME ("surname"),
    EMAIL ("email"),
    LOGIN ("login"),
    PASSWORD("password"),
    PASSWORD_CONFIRMING ("passwordConfirming");

    private final String value;

    RequestParameter(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
