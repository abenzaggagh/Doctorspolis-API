package com.doctorspolis.backend.controller;

import com.doctorspolis.backend.model.DTO.AuthenticationRequestDTO;
import com.doctorspolis.backend.model.DTO.AuthenticationResponseDTO;
import com.doctorspolis.backend.model.DTO.UserDTO;
import com.doctorspolis.backend.model.User;
import com.doctorspolis.backend.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


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
    public ResponseEntity<AuthenticationResponseDTO> signIn(@RequestBody @Valid AuthenticationRequestDTO authenticationRequestDTO) {
        return ResponseEntity.ok(this.authenticationService.signIn(authenticationRequestDTO));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<AuthenticationResponseDTO> signUp(@RequestBody @Valid AuthenticationRequestDTO authenticationRequestDTO) {
        return ResponseEntity.ok(this.authenticationService.signUp(authenticationRequestDTO));
    }

    @PostMapping("/sign-out")
    public String signOut() {
        return "Doctorspolis";
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDTO> profile(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(this.authenticationService.profile(user));
    }

}
