package com.dev.SocialMedia.service;

import com.dev.SocialMedia.repository.UserRepository;
import com.dev.SocialMedia.utils.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private JwtUtil jwtUtil;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

}
