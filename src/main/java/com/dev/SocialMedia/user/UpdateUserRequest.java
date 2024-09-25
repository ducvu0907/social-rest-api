package com.dev.SocialMedia.user;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String username;
    private String bio;
}
