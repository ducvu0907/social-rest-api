package com.dev.SocialMedia.controller;

import com.dev.SocialMedia.dto.LoginUserDto;
import com.dev.SocialMedia.dto.RegisterUserDto;
import com.dev.SocialMedia.dto.UserDto;
import com.dev.SocialMedia.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // test api
    @GetMapping("/test")
    public String test() {
        return "hello world";
    }
    // end test

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody RegisterUserDto registerUserDto) {
        UserDto registeredUser = authService.signup(registerUserDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginUserDto loginUserDto) {
        UserDto loggedInUser = authService.login(loginUserDto);
        return ResponseEntity.ok((loggedInUser));
    }
}
