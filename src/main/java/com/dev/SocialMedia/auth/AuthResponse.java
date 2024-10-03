package com.dev.SocialMedia.auth;

import lombok.Data;

@Data
public class AuthResponse {
    private String accessToken;
    private String refreshToken;
}
