package com.example.paysystem.exception;

public class NoHasAccessForThisInformation extends RuntimeException{
    public NoHasAccessForThisInformation() {
        super();
    }

    public NoHasAccessForThisInformation(String message) {
        super(message);
    }

    public NoHasAccessForThisInformation(String message, Throwable cause) {
        super(message, cause);
    }

    public NoHasAccessForThisInformation(Throwable cause) {
        super(cause);
    }
}
