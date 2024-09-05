package com.dev.SocialMedia.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserProfileDto {
    private String username;
    private String avatarUrl;
    private String bio;
    private int followingsCount;
    private int followersCount;
}
