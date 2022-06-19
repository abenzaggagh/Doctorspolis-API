package com.doctorspolis.backend.controller;

import com.doctorspolis.backend.model.DTO.AuthenticationRequestDTO;
import com.doctorspolis.backend.security.JwtTokenProvider;
import com.doctorspolis.backend.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin
@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<String> signin(@RequestBody AuthenticationRequestDTO authenticationRequestDTO) {
        try {
            String username = authenticationRequestDTO.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, authenticationRequestDTO.getPassword()));
            String token = jwtTokenProvider.createToken(username);
            return ok(token);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("vc");
        }
        // return ResponseEntity.ok().body(this.authenticationService.signin());
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
