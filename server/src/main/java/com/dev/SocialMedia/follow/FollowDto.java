package com.dev.SocialMedia.follow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class FollowDto {
    private String followerName;
    private String followerAvatarUrl;
    private String followedName;
    private String followedAvatarUrl;
}
