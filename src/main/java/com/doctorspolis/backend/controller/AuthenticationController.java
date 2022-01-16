package com.doctorspolis.backend.controller;

import com.doctorspolis.backend.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<String> signin() {
        return ResponseEntity.ok().body(this.authenticationService.signin());
    }

    @PostMapping("/sign-up")
    public String signup() {
        return "Doctorspolis";
    }

    @PostMapping("/sign-out")
    public String signout() {
        return "Doctorspolis";
    }

    @GetMapping("/profile")
    public String profile() {
        return "Doctorspolis";
    }

}
