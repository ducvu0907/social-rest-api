package com.dev.SocialMedia.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String avatarUrl;
    private String bio;
    private int followingsCount;
    private int followersCount;
}
