package com.dev.SocialMedia.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String profilePicture;
    private String bio;
    private int followingsCount;
    private int followersCount;
}
