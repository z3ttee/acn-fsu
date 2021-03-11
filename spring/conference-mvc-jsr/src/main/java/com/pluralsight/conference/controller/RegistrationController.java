package com.pluralsight.conference.controller;

import com.pluralsight.conference.model.Registration;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @GetMapping("/registration")
    public String getRegistration(@ModelAttribute("registration") Registration registration) {
        return "registration";
    }

    // Using validation (@Valid, rules specified in model class), we get passed an object of BindingResult
    // With this, we can check for validation errors
    @PostMapping("/registration")
    public String addRegistration(@Valid @ModelAttribute("registration") Registration registration, BindingResult result) {
        if(result.hasErrors()) {
            System.out.println("There are validation errors");
            return "registration";
        }

        System.out.println(registration.getName());

        // Post-Redirect-Get:
        // Redirect back to registration -> so information will be cleared -> no re-submit
        return "redirect:registration";
    }

}
