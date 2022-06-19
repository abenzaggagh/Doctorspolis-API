package com.doctorspolis.backend.service;

import com.doctorspolis.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    @Autowired
    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String signin() {
        // userRepository.save(new User("Amine", "", "BEN ZAGGAGH"));
        return "Doctorspolis";
    }

}
