package com.doctorspolis.backend.service;

import com.doctorspolis.backend.helper.mapper.UserMapper;
import com.doctorspolis.backend.model.DTO.UserDTO;
import com.doctorspolis.backend.model.DTO.authentication.AuthenticationRequestDTO;
import com.doctorspolis.backend.model.DTO.authentication.AuthenticationResponseDTO;
import com.doctorspolis.backend.model.User;
import com.doctorspolis.backend.model.enumeration.Role;
import com.doctorspolis.backend.repository.UserRepository;
import com.doctorspolis.backend.security.JwtTokenProvider;
import com.doctorspolis.backend.utility.constants.DoctorspolisConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;


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
        AuthenticationResponseDTO authenticationResponseDTO = authenticate(authenticationRequestDTO.getUsername(), authenticationRequestDTO.getPassword());

        authenticationResponseDTO.setUser(userMapper.toDTO(userRepository.getUserByUsername(authenticationRequestDTO.getUsername())));

        return authenticationResponseDTO;
    }

    // TODO: Change the request to add the user's details in the request body.
    @Transactional
    public AuthenticationResponseDTO signUp(AuthenticationRequestDTO authenticationRequestDTO) {
        AuthenticationResponseDTO authenticationResponseDTO;

        try {

            String username = authenticationRequestDTO.getUsername();

            if (!userRepository.existsByUsername(username)) {

                if (ObjectUtils.isEmpty(authenticationRequestDTO.getUser())) {

                }

                User user = userRepository.save(User.builder()
                        .password(passwordEncoder.encode(authenticationRequestDTO.getPassword()))
                        .username(authenticationRequestDTO.getUsername())
                        .refreshToken(DoctorspolisConstants.EMPTY)
                        .role(Role.USER)
                        .enabled(true)
                        .build());

                authenticationResponseDTO = authenticate(username, authenticationRequestDTO.getPassword());

                userRepository.setRefreshToken(username, authenticationResponseDTO.getRefreshToken());

                authenticationResponseDTO.setUser(userMapper.toDTO(user));

            } else {

                authenticationResponseDTO = signIn(authenticationRequestDTO);

            }

            return authenticationResponseDTO;

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("BAD CREDENTIALS EXCEPTION");
        }
    }

    public UserDTO profile(User user) {
        return userMapper.toDTO(userRepository.getUserByUsername(user.getUsername()));
    }

    private AuthenticationResponseDTO authenticate(String username, String password) throws AuthenticationException {
        if (authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password)) != null) {
            return AuthenticationResponseDTO
                    .builder()
                    .accessToken(jwtTokenProvider.createAccessToken(username))
                    .refreshToken(jwtTokenProvider.createRefreshToken())
                    .build();
        }

        throw new BadCredentialsException("BAD CREDENTIALS EXCEPTION");
    }

}
