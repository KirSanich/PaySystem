package com.example.paysystem.service.email;

public interface EmailService {

    void sendMessage(String toEmail, String body, String subject);
}
