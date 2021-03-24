package com.pluralsight.conference.listener;

import com.pluralsight.conference.event.OnPasswordResetEvent;
import com.pluralsight.conference.model.Password;
import com.pluralsight.conference.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PasswordListener implements ApplicationListener<OnPasswordResetEvent> {

    private String serverUrl = "http://localhost:8080/";

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private PasswordService passwordService;

    @Override
    public void onApplicationEvent(OnPasswordResetEvent event) {
        this.resetPassword(event);
    }

    private void resetPassword(OnPasswordResetEvent event) {
        // Create password token
        Password password = event.getPassword();
        String token = UUID.randomUUID().toString();
        passwordService.createResetToken(password, token);

        // Get email properties
        String recipientAddress = password.getEmail();
        String subject = "Reset password";
        String confirmationUrl = event.getAppUrl() + "/passwordReset?token=" + token;
        String message = "Reset password";

        // Send email
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("springmail@tsalliance.eu");
        mailMessage.setTo(recipientAddress);
        mailMessage.setSubject(subject);
        mailMessage.setText(message + "\r\n" + serverUrl + confirmationUrl);
        mailSender.send(mailMessage);

    }
}
