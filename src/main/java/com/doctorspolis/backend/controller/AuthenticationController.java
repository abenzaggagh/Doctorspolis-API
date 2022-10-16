package com.doctorspolis.backend.controller;

import com.doctorspolis.backend.model.DTO.UserDTO;
import com.doctorspolis.backend.model.DTO.authentication.AuthenticationRequestDTO;
import com.doctorspolis.backend.model.DTO.authentication.AuthenticationResponseDTO;
import com.doctorspolis.backend.model.User;
import com.doctorspolis.backend.service.AuthenticationService;
import com.doctorspolis.backend.utility.constants.DoctorspolisConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin
@RestController
@RequestMapping(DoctorspolisConstants.AUTHENTICATION)
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
    public ResponseEntity<Boolean> signOut(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(authenticationService.signOut(user));
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDTO> profile(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(this.authenticationService.profile(user));
    }

}
