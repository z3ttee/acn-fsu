package com.pluralsight.conference.controller;

import com.pluralsight.conference.event.OnCreateAccountEvent;
import com.pluralsight.conference.model.Account;
import com.pluralsight.conference.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @GetMapping("account")
    public String getAccount(@ModelAttribute("account") Account account) {
        return "account";
    }

    @PostMapping("account")
    public String addAccount(@Valid @ModelAttribute("account") Account account, BindingResult result) {
        // Check for errors in binding result


        // Check if account does not exist


        // Validate E-Mail address


        // Encrypt password
        account.setPassword(passwordEncoder.encode(account.getPassword()));

        // Create the new account
        account = accountService.create(account);

        // Fire an event on creation
        eventPublisher.publishEvent(new OnCreateAccountEvent(account, "conference"));

        return "redirect:account";
    }

    @GetMapping("accountConfirm")
    public String confirmAccount(@RequestParam("token") String token) {
        accountService.confirmAccount(token);
        return "accountConfirmed";
    }

}
