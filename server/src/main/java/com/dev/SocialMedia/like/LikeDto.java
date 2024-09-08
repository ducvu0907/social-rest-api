package com.dev.SocialMedia.like;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LikeDto {
    private String username;
    private String userAvatarUrl;
    private Long postId;
}
