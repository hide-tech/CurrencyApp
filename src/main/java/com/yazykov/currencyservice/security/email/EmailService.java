package com.yazykov.currencyservice.security.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService implements EmailSender {

    private final JavaMailSender mailSender;

    @Override
    @Async
    public void send(String to, String message) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,"utf-8");
            helper.setText(message, true);
            helper.setTo(to);
            helper.setSubject("Confirm your email on CurrencyApp");
            helper.setFrom("currency@app.com");
            mailSender.send(mimeMessage);
            log.info("mimeMessage has been successfully sent");
        } catch (MessagingException ex){
            log.error("sending message fault", ex);
            throw new IllegalStateException("sending message fault");
        }
    }
}
