package com.doctorspolis.service;

import com.doctorspolis.configuration.security.JwtTokenProvider;
import com.doctorspolis.controller.exception.AuthenticationException;
import com.doctorspolis.model.data.doctor.Doctor;
import com.doctorspolis.model.data.authentication.OTPCode;
import com.doctorspolis.model.data.Patient;
import com.doctorspolis.model.data.authentication.User;
import com.doctorspolis.model.dto.authentication.*;
import com.doctorspolis.utility.mapper.PatientMapper;
import com.doctorspolis.repository.DoctorRepository;
import com.doctorspolis.repository.OTPCodeRepository;
import com.doctorspolis.repository.PatientRepository;
import com.doctorspolis.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.Random;

import static com.doctorspolis.controller.exception.AuthenticationExceptionEnum.*;
import static com.doctorspolis.model.enumuration.Role.DOCTOR;
import static com.doctorspolis.model.enumuration.Role.PATIENT;

@RequiredArgsConstructor

@Slf4j
@Service
public class AuthenticationService {

    private final Random random = new Random();

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final OTPCodeRepository otpCodeRepository;

    private final PatientMapper patientMapper;

    public AuthenticationResponseDTO signIn(SignInRequestDTO signInRequestDTO) {
        val username = signInRequestDTO.getEmail();
        val password = signInRequestDTO.getPassword();

        AuthenticationResponseDTO authenticationResponseDTO = authenticate(username, password);

        User user = userRepository.getUserByEmail(username);

        user.setRefreshToken(authenticationResponseDTO.getRefreshToken());

        userRepository.save(user);

        switch (user.getRole()) {
            case DOCTOR -> {}
            case PATIENT -> authenticationResponseDTO.setPatient(patientMapper.toDTO(patientRepository.findByUser(user).orElseThrow()));
        }

        return authenticationResponseDTO;
    }

    @Transactional
    public AuthenticationResponseDTO signUp(SignUpRequestDTO signUpRequestDTO) {

        if (userRepository.existsByEmail(signUpRequestDTO.getEmail())) {
            throw new AuthenticationException(USER_EMAIL_ALREADY_EXISTS);
        }

        if (userRepository.existsByPhone(signUpRequestDTO.getPhone())) {
            throw new AuthenticationException(USER_PHONE_ALREADY_EXISTS);
        }

        switch (signUpRequestDTO.getRole()) {
            case DOCTOR -> {
                return signUpDoctor(signUpRequestDTO);
            }
            case PATIENT -> {
                return signUpPatient(signUpRequestDTO);
            }
        }

        return null;
    }

    @Transactional
    public AuthenticationResponseDTO signUpDoctor(SignUpRequestDTO signUpRequestDTO) {
        val email = signUpRequestDTO.getEmail();
        val phone = signUpRequestDTO.getPhone();
        val password = signUpRequestDTO.getPassword();

        User user = getUser(email, phone, password);
        user.setRole(DOCTOR);

        Doctor doctor = new Doctor();
        doctor.setUser(user);
        doctor.setFirstname(signUpRequestDTO.getFirstName());
        doctor.setLastname(signUpRequestDTO.getLastName());
        doctor.setGender(signUpRequestDTO.getGender());
        doctor.setBirthday(signUpRequestDTO.getBirthday());
        doctor.setIsProfileVisible(false);

        this.userRepository.save(user);
        this.doctorRepository.save(doctor);

        val response = this.authenticate(email, password);

        userRepository.setRefreshToken(email, response.getRefreshToken());

        return response;
    }

    @Transactional
    public AuthenticationResponseDTO signUpPatient(SignUpRequestDTO signUpRequestDTO) {
        val email = signUpRequestDTO.getEmail();
        val phone = signUpRequestDTO.getPhone();
        val password = signUpRequestDTO.getPassword();

        User user = getUser(email, phone, password);
        user.setRole(PATIENT);

        Patient patient = new Patient();
        patient.setUser(user);
        patient.setFirstname(signUpRequestDTO.getFirstName());
        patient.setLastname(signUpRequestDTO.getLastName());
        patient.setGender(signUpRequestDTO.getGender());
        patient.setBirthday(signUpRequestDTO.getBirthday());

        this.userRepository.save(user);
        this.patientRepository.save(patient);

        val response = this.authenticate(email, password);

        userRepository.setRefreshToken(email, response.getRefreshToken());

        response.setPatient(patientMapper.toDTO(patient));

        return response;
    }

    private User getUser(String email, String phone, String password) {
        User user = new User();

        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(passwordEncoder.encode(password));
        user.setRefreshToken(jwtTokenProvider.createRefreshToken(email));

        return user;
    }


    @Transactional
    public Boolean signOut(User user) {
        if (!ObjectUtils.isEmpty(user) && !ObjectUtils.isEmpty(user.getUsername()) && !ObjectUtils.isEmpty(user.getRefreshToken())) {
            userRepository.setRefreshToken(user.getUsername(), null);
            return true;
        }

        return false;
    }


    public AuthenticationResponseDTO profile(User user) {
        if (!ObjectUtils.isEmpty(user) && !ObjectUtils.isEmpty(user.getRefreshToken())) {
            switch (user.getRole()) {
                case DOCTOR -> {
                    // return AuthenticationResponseDTO.builder().doctor()
                }
                case PATIENT -> {
                    return AuthenticationResponseDTO
                            .builder()
                            .patient(patientMapper.toDTO(patientRepository.findByUser(user).orElseThrow()))
                            .build();
                }
            }
        }

        return null;
    }

    /**
     * Check if the email is already used
     * @param emailVerificationDTO Contains the email address
     * @return true if email exists in the database, false otherwise.
     */
    public Boolean checkEmail(EmailVerificationDTO emailVerificationDTO) {
        return userRepository.existsByEmail(emailVerificationDTO.getEmail());
    }

    /**
     * Check if the phone is already used
     * @param phoneVerificationDTO Contains the phone number
     * @return true if phone number exists in the database, false otherwise.
     */
    public Boolean checkPhone(PhoneVerificationDTO phoneVerificationDTO) {
        return userRepository.existsByPhone(phoneVerificationDTO.getPhone());
    }

    /**
     * Generate OTP Code for phone verification
     * @param phoneVerificationDTO Phone number to verify
     * @return OTP Code Hashed and Phone number
     */
    public PhoneOTPVerificationDTO verifyPhone(PhoneVerificationDTO phoneVerificationDTO) {
        val phone = phoneVerificationDTO.getPhone();

        val otpCode = generateOrRenewOTPCode(phone);

        val response = new PhoneOTPVerificationDTO();
        response.setOtpCode(otpCode.getOtpCode());
        response.setPhone(phone);

        return response;
    }

    private OTPCode generateOrRenewOTPCode(String phone) {
        val otpCodes = otpCodeRepository.findByPhoneAndExpiryDateIsAfterAndIsUsed(phone, LocalDateTime.now(), false);

        int randomFourDigits = random.nextInt(10000);
        String code = String.format("%4d", randomFourDigits);

        log.info("OTP Code : {}", code);
        // todo: send the otp code via sms

        otpCodes.forEach(otpCode -> {
            otpCode.setIsExpired(true);
            otpCode.setExpiryDate(LocalDateTime.now());
        });

        val otpCode = new OTPCode();
        otpCode.setPhone(phone);
        otpCode.setOtpCode(passwordEncoder.encode(code));
        otpCode.setExpiryDate(LocalDateTime.now().plusMinutes(5));

        otpCodes.add(otpCode);

        otpCodeRepository.saveAll(otpCodes);

        return otpCode;
    }

    public Boolean verifyOTPCode(PhoneOTPVerificationDTO phoneOTPVerificationDTO) {
        val otpCode = otpCodeRepository.findByPhoneAndIsUsedAndIsExpired(phoneOTPVerificationDTO.getPhone(), false, false)
                .orElseThrow(() -> new AuthenticationException(INCORRECT_OTP_CODE));

        if (passwordEncoder.matches(phoneOTPVerificationDTO.getOtpCode(), otpCode.getOtpCode())) {
            otpCode.setIsExpired(true);
            otpCode.setIsUsed(true);
            otpCode.setExpiryDate(LocalDateTime.now());

            otpCodeRepository.save(otpCode);

            return true;
        };

        throw new AuthenticationException(INCORRECT_OTP_CODE);
    }

    
    @Transactional
    public AuthenticationResponseDTO refreshTokens(String refreshToken) {
        String email = jwtTokenProvider.getUsername(refreshToken);

        User user = userRepository.findUserByRefreshToken(refreshToken)
                .orElseThrow(() -> new AuthenticationException(UNKNOWN_EXCEPTION));

        if (user.getEmail().equals(email)) {

            val newRefreshToken = jwtTokenProvider.createRefreshToken(email);

            user.setRefreshToken(newRefreshToken);
            userRepository.setRefreshToken(email, newRefreshToken);
            
            return AuthenticationResponseDTO
                    .builder()
                    .accessToken(jwtTokenProvider.createAccessToken(email))
                    .refreshToken(newRefreshToken)
                    .issuedAt(System.currentTimeMillis())
                    .expireAt(System.currentTimeMillis() + jwtTokenProvider.getAccessExpirationPeriod())
                    .build();
        }

        throw new AuthenticationException(USER_NOT_FOUND);
    }


    /**
     * Method authenticate the user using his email and password.
     * @param username - user's email
     * @param password - user's password
     * @return AuthenticationResponseDTO
     * @throws AuthenticationException -
     */
    private AuthenticationResponseDTO authenticate(String username, String password) {
        try {
            val usernamePassword = new UsernamePasswordAuthenticationToken(username, password);
            val authentication = authenticationManager.authenticate(usernamePassword);
            if (!ObjectUtils.isEmpty(authentication) && authentication.isAuthenticated()) {
                return AuthenticationResponseDTO
                        .builder()
                        .accessToken(jwtTokenProvider.createAccessToken(username))
                        .refreshToken(jwtTokenProvider.createRefreshToken(username))
                        .issuedAt(System.currentTimeMillis())
                        .expireAt(System.currentTimeMillis() + jwtTokenProvider.getAccessExpirationPeriod())
                        .build();
            }
        } catch (InternalAuthenticationServiceException internalAuthenticationServiceException) {
            throw new AuthenticationException(USER_NOT_FOUND);
        } catch (BadCredentialsException badCredentialsException) {
            throw new AuthenticationException(BAD_CREDENTIALS);
        }

        throw new AuthenticationException(UNKNOWN_EXCEPTION);
    }



}
