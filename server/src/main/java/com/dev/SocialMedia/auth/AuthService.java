package com.dev.SocialMedia.auth;

import com.dev.SocialMedia.common.ApiResponse;
import com.dev.SocialMedia.config.JwtService;
import com.dev.SocialMedia.entity.User;
import com.dev.SocialMedia.exception.CustomException;
import com.dev.SocialMedia.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // hash password
        user.setAvatarUrl("https://ui-avatars.com/api/?name=" + user.getUsername());
        user.setBio("");
        user.setPosts(new ArrayList<>());
        user.setComments(new ArrayList<>());
        user.setFollowing(new ArrayList<>());
        user.setFollowers(new ArrayList<>());
        userRepository.save(user);

        return new ApiResponse("success", "register successfully", null);
    }

    public ApiResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        return new ApiResponse("success", "login successfully", jwtToken);
    }

}
