package com.pluralsight.conference.listener;

import com.pluralsight.conference.event.OnCreateAccountEvent;
import com.pluralsight.conference.model.Account;
import com.pluralsight.conference.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.UUID;

// Because an event can be a component directly in spring (as ApplicationListener)
// we can mark it as such using @Component
// Using this annotation, a bean is created to make sure only one instance is being registered

@Component
public class AccountListener implements ApplicationListener<OnCreateAccountEvent> {

    private final String SERVER_URL = "http://localhost:8080/";

    @Autowired
    private AccountService accountService;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnCreateAccountEvent event) {
        this.confirmCreateAccount(event);
    }

    @Transactional
    public void confirmCreateAccount(OnCreateAccountEvent event) {
        // Get the account
        Account account = event.getAccount();

        // Create verification token
        String token = UUID.randomUUID().toString();
        accountService.createVerificationToken(account, token);

        // Get email properties
        String recipientAddress = account.getEmail();
        String subject = "Account Confirmation";
        String confirmationUrl = event.getAppUrl() + "/accountConfirm?token=" + token;
        String message = "Please confirm your account registration";

        // Send email
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setFrom("springmail@tsalliance.eu");
        email.setText(message + "\r\n" + SERVER_URL + confirmationUrl);
        mailSender.send(email);
    }

}
