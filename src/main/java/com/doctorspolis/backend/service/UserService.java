package com.doctorspolis.backend.service;

import com.doctorspolis.backend.controller.exception.ResourceNotFoundException;
import com.doctorspolis.backend.model.User;
import com.doctorspolis.backend.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username).orElseThrow(() -> new ResourceNotFoundException(username));
    }

    public Boolean isAuthenticated(String username) {
        User user = userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        if (!ObjectUtils.isEmpty(user.getRefreshToken())) {
            // TODO: Check Refresh token if it's valid, if not valid set null and return false
            return true;
        }

        return false;
    }

}
