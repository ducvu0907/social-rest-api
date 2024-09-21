package com.dev.SocialMedia.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CommentDetailsDto {
    private Long id;
    private String username;
    private String userAvatarUrl;
    private Long postId;
    private String content;
}
