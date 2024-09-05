package com.dev.SocialMedia.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PostContentDto {
    private String content;
    private String username;
    private String avatarUrl;
}
