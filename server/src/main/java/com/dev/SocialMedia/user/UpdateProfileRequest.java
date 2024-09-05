package com.dev.SocialMedia.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UpdateProfileRequest {
    private String username;
    private String bio;
    // update avatar after implementing file uploading
    // private String avatarUrl;
}
