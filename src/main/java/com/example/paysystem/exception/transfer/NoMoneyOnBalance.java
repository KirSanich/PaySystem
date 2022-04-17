package com.example.paysystem.exception.transfer;

public class NoMoneyOnBalance extends TransferException{
    public NoMoneyOnBalance() {
        super();
    }

    public NoMoneyOnBalance(String message) {
        super(message);
    }

    public NoMoneyOnBalance(String message, Throwable cause) {
        super(message, cause);
    }

    public NoMoneyOnBalance(Throwable cause) {
        super(cause);
    }
}
