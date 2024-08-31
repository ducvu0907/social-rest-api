package com.dev.SocialMedia.controller;

import com.dev.SocialMedia.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/")
    public String signup() {
        return "hello from auth get controller";
    }

}
