package com.dev.SocialMedia.controller;

import com.dev.SocialMedia.dto.UserDto;
import com.dev.SocialMedia.dto.UserLoginDto;
import com.dev.SocialMedia.dto.UserRegisterDto;
import com.dev.SocialMedia.entity.User;
import com.dev.SocialMedia.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    private ResponseEntity<UserDto> register(@RequestBody UserRegisterDto dto) {
        var response = userService.register(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    private ResponseEntity<UserDto> login(@RequestBody UserLoginDto dto) {
        var response = userService.login(dto);
        return ResponseEntity.ok(response);
    }

}
