package com.dev.SocialMedia.post;

import lombok.Data;

@Data
public class CreatePostRequest {
    private String username;
    private String content;
}
