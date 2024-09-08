package com.dev.SocialMedia.comment;

import lombok.Data;

@Data
public class CreateCommentRequest {
    private Long userId;
    private String content;
}
