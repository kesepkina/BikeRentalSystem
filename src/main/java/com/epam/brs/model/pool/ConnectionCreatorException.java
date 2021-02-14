package com.epam.brs.model.pool;

public class ConnectionCreatorException extends Exception{
    public ConnectionCreatorException() {
        super();
    }

    public ConnectionCreatorException(String message) {
        super(message);
    }

    public ConnectionCreatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionCreatorException(Throwable cause) {
        super(cause);
    }
}
