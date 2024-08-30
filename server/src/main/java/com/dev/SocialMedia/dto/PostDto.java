package com.dev.SocialMedia.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostDto {
    private Long id;
    private String content;
    private UserDto user;
    private int likesCount;
    private int commentsCount;
    private LocalDateTime createdAt;
}
