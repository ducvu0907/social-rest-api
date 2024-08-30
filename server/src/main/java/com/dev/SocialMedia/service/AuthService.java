package com.dev.SocialMedia.service;

import com.dev.SocialMedia.dto.LoginUserDto;
import com.dev.SocialMedia.dto.RegisterUserDto;
import com.dev.SocialMedia.dto.UserDto;
import com.dev.SocialMedia.entity.User;
import com.dev.SocialMedia.repository.UserRepository;
import com.dev.SocialMedia.utils.JwtUtil;
import com.dev.SocialMedia.utils.Mapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthService {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthService(
            JwtUtil jwtUtil, UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager
    ) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public UserDto signup(RegisterUserDto input) {
        User user = new User();
        user.setUsername(input.getUsername());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword())); // hash password
        user.setBio("");
        user.setAvatarUrl("https://ui-avatars.com/api/?name=" + user.getUsername()); // default avatar url
        user.setPosts(new ArrayList<>());
        user.setComments(new ArrayList<>());
        user.setLikes(new ArrayList<>());
        user.setFollowers(new ArrayList<>());
        user.setFollowing(new ArrayList<>());
        return Mapper.mapUserToUserDto(userRepository.save(user));
    }

    // TODO: jwt ?
    public UserDto login(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );
        User user = userRepository.findByUsername(input.getUsername()).orElseThrow();
        return Mapper.mapUserToUserDto(user);
    }
}
