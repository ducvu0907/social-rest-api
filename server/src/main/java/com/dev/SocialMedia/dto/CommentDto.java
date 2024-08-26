package com.dev.SocialMedia.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {
    private Long id;
    private String content;
    private UserDto author;
    private LocalDateTime createdAt;
}
