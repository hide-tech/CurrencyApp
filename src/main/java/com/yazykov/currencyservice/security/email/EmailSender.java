package com.yazykov.currencyservice.security.email;

public interface EmailSender {
    void send(String to, String message);
}
