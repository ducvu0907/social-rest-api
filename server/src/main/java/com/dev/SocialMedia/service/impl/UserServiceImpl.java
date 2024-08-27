package com.dev.SocialMedia.service.impl;

import com.dev.SocialMedia.dto.UserDto;
import com.dev.SocialMedia.dto.UserLoginDto;
import com.dev.SocialMedia.dto.UserRegisterDto;
import com.dev.SocialMedia.entity.User;
import com.dev.SocialMedia.repository.UserRepository;
import com.dev.SocialMedia.service.IUserService;
import com.dev.SocialMedia.utils.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto register(UserRegisterDto dto) {
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new IllegalArgumentException("passwords do not match");
        }

        if (repository.findByUsername(dto.getUsername()) != null) {
            throw new IllegalArgumentException("username already exists");
        }

        if (repository.findByEmail(dto.getEmail()) != null) {
            throw new IllegalArgumentException("email already exists");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        User savedUser = repository.save(user);
        return Mapping.mapUserToUserDto(savedUser);
    }

    @Override
    public UserDto login(UserLoginDto dto) {
        User user = repository.findByEmail(dto.getEmail());

        if (user == null) {
            throw new UsernameNotFoundException("email does not exist");
        }

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("password incorrect");
        }

        return Mapping.mapUserToUserDto(user);
    }

    // TODO: might not need this
    @Override
    public void logout() {

    }

}
