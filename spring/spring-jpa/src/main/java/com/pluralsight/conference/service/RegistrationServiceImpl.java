package com.pluralsight.conference.service;

import com.pluralsight.conference.model.Registration;
import com.pluralsight.conference.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegistrationRepository repository;

    @Override
    @Transactional
    public Registration addRegistration(Registration registration) {
        return repository.save(registration);
    }

}
