package com.exceptions;

public class FlightsAppException extends Exception{
    public FlightsAppException() {
        super();
    }

    public FlightsAppException(String message) {
        super(message);
    }

    public FlightsAppException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlightsAppException(Throwable cause) {
        super(cause);
    }

    protected FlightsAppException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
