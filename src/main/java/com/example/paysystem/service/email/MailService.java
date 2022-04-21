package com.example.paysystem.service.email;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class MailService implements EmailService {

    @Autowired
    private final JavaMailSender mailSender;

    @Override
    @Async
    public void sendMessage(String toEmail, String body, String subject) {

        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("bookingsystem@payment.com");
            simpleMailMessage.setTo(toEmail);
            simpleMailMessage.setText(body);
            simpleMailMessage.setSubject(subject);
            mailSender.send(simpleMailMessage);
        } catch (MailException e) {
            log.info("Something go wrong when we try to send email");
        }

    }
}
