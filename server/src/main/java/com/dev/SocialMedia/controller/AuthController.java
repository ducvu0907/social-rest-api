package com.dev.SocialMedia.controller;

import com.dev.SocialMedia.dto.UserDto;
import com.dev.SocialMedia.dto.UserLoginDto;
import com.dev.SocialMedia.dto.UserRegisterDto;
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
    public ResponseEntity<UserDto> register(@RequestBody UserRegisterDto userRegisterDto) {
        var response = userService.register(userRegisterDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody UserLoginDto userLoginDto) {
        var response = userService.login(userLoginDto);
        return ResponseEntity.ok(response);
    }

}
