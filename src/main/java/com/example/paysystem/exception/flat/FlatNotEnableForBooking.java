package com.example.paysystem.exception.flat;

public class FlatNotEnableForBooking extends FlatException{
    public FlatNotEnableForBooking() {
    }

    public FlatNotEnableForBooking(String message) {
        super(message);
    }

    public FlatNotEnableForBooking(String message, Throwable cause) {
        super(message, cause);
    }

    public FlatNotEnableForBooking(Throwable cause) {
        super(cause);
    }
}
