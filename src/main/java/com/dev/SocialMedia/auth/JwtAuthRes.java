package com.dev.SocialMedia.auth;

import lombok.Data;

@Data
public class JwtAuthRes{
    private String accessToken;
    public JwtAuthRes(String accessToken) {
        this.accessToken = accessToken;
    }
}
