package com.doctorspolis.backend.service;

import com.doctorspolis.backend.helper.mapper.UserMapper;
import com.doctorspolis.backend.model.DTO.AuthenticationRequestDTO;
import com.doctorspolis.backend.model.DTO.AuthenticationResponseDTO;
import com.doctorspolis.backend.model.DTO.UserDTO;
import com.doctorspolis.backend.model.User;
import com.doctorspolis.backend.repository.UserRepository;
import com.doctorspolis.backend.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    private final AuthenticationManager authenticationManager;


    @Autowired
    public AuthenticationService(UserMapper userMapper,
                                 UserRepository userRepository,
                                 PasswordEncoder passwordEncoder,
                                 JwtTokenProvider jwtTokenProvider,
                                 AuthenticationManager authenticationManager) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponseDTO signIn(AuthenticationRequestDTO authenticationRequestDTO) {
        return authenticate(authenticationRequestDTO.getUsername(), authenticationRequestDTO.getPassword());
    }

    public AuthenticationResponseDTO signUp(AuthenticationRequestDTO authenticationRequestDTO) {
        try {

            String username = authenticationRequestDTO.getUsername();

            if (!userRepository.existsByUsername(username)) {
                userRepository.save(User.builder()
                        .password(passwordEncoder.encode(authenticationRequestDTO.getPassword()))
                        .username(authenticationRequestDTO.getUsername())
                        .email(authenticationRequestDTO.getEmail())
                        .enabled(true)
                        .build());
            }

            return authenticate(username, authenticationRequestDTO.getPassword());
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("vc");
        }
    }

    public UserDTO profile(User user) {
        return userMapper.toDTO(user);
    }

    private AuthenticationResponseDTO authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        return AuthenticationResponseDTO
                .builder()
                .accessToken(jwtTokenProvider.createAccessToken(username))
                .refreshToken(jwtTokenProvider.createAccessToken(username))
                .build();
    }

}
