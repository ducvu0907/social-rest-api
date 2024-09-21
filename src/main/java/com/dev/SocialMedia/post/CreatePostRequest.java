package com.dev.SocialMedia.post;

import lombok.Data;

@Data
public class CreatePostRequest {
    private Long userId;
    private String content;
}
