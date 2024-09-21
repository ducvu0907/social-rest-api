package com.dev.SocialMedia.auth;

import com.dev.SocialMedia.common.ApiResponse;
import com.dev.SocialMedia.config.JwtService;
import com.dev.SocialMedia.user.User;
import com.dev.SocialMedia.exception.CustomException;
import com.dev.SocialMedia.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public ApiResponse register(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new CustomException("username already exists");
        }
        if (userRepository.findByUsername(request.getEmail()).isPresent()) {
            throw new CustomException("email already exists");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .avatarUrl("https://ui-avatars.com/api/?name=" + request.getUsername()) // tempo avatar api
                .bio("")
                .posts(new ArrayList<>())
                .comments(new ArrayList<>())
                .following(new ArrayList<>())
                .followers(new ArrayList<>())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        userRepository.save(user);

        return new ApiResponse("success", "register successfully", null);
    }

    public ApiResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        // System.out.println(authentication.getPrincipal());

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        String jwtToken = jwtService.generateToken(user);

        return new ApiResponse("success", "login successfully", jwtToken);
    }

}