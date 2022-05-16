package com.yazykov.currencyservice.throwable;

public class ConnectionToBankException extends Exception{
    public ConnectionToBankException() {
    }

    public ConnectionToBankException(String message) {
        super(message);
    }

    public ConnectionToBankException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionToBankException(Throwable cause) {
        super(cause);
    }
}
