package com.dev.SocialMedia.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LikeDto {
    private Long id;
    private UserDto user;
    private PostDto post;
    private LocalDateTime createdAt;
}
