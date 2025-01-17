package com.doctorspolis.controller;

import com.doctorspolis.model.data.User;
import com.doctorspolis.model.dto.authentication.*;
import com.doctorspolis.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.doctorspolis.utility.DoctorspolisConstants.*;

@RequiredArgsConstructor

@RestController
@RequestMapping(AUTHENTICATION_ENDPOINT)
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping(SIGN_IN_ENDPOINT)
    public ResponseEntity<AuthenticationResponseDTO> signIn(@RequestBody @Valid SignInRequestDTO signInRequestDTO) {
        return ResponseEntity.ok(this.authenticationService.signIn(signInRequestDTO));
    }

    @PostMapping(SIGN_UP_ENDPOINT)
    public ResponseEntity<AuthenticationResponseDTO> signUp(@RequestBody @Valid SignUpRequestDTO signUpRequestDTO) {
        return ResponseEntity.ok(this.authenticationService.signUp(signUpRequestDTO));
    }

    @PostMapping(SIGN_OUT_ENDPOINT)
    public ResponseEntity<Boolean> signOut(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(authenticationService.signOut(user));
    }

    @GetMapping(PROFILE_ENDPOINT)
    public ResponseEntity<AuthenticationResponseDTO> profile(@AuthenticationPrincipal User authenticationUser) {
        return ResponseEntity.ok(this.authenticationService.profile(authenticationUser));
    }

    @PostMapping(CHECK_EMAIL_ENDPOINT)
    public ResponseEntity<Boolean> checkEmail(@RequestBody @Valid EmailVerificationDTO emailVerificationDTO) {
        return ResponseEntity.ok(this.authenticationService.checkEmail(emailVerificationDTO));
    }

    @PostMapping(CHECK_PHONE_ENDPOINT)
    public ResponseEntity<Boolean> checkPhone(@RequestBody @Valid PhoneVerificationDTO phoneVerificationDTO) {
        return ResponseEntity.ok(this.authenticationService.checkPhone(phoneVerificationDTO));
    }

    @PostMapping(VERIFY_PHONE_ENDPOINT)
    public ResponseEntity<PhoneOTPVerificationDTO> verifyPhone(@RequestBody PhoneVerificationDTO phoneVerificationDTO) {
        return ResponseEntity.ok(this.authenticationService.verifyPhone(phoneVerificationDTO));
    }

    @PostMapping(VERIFY_OTP_ENDPOINT)
    public ResponseEntity<Boolean> verifyOTPCode(@RequestBody PhoneOTPVerificationDTO phoneOTPVerificationDTO) {
        return ResponseEntity.ok(this.authenticationService.verifyOTPCode(phoneOTPVerificationDTO));
    }

    @PostMapping(REFRESH_TOKENS_ENDPOINT)
    public ResponseEntity<AuthenticationResponseDTO> refreshTokens(@RequestBody RefreshTokenDTO refreshToken) {
        return ResponseEntity.ok(this.authenticationService.refreshTokens(refreshToken.getRefreshToken()));
    }







}
