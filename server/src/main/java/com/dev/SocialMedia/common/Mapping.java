package com.dev.SocialMedia.common;

import com.dev.SocialMedia.entity.Post;
import com.dev.SocialMedia.entity.User;
import com.dev.SocialMedia.post.PostContentDto;
import com.dev.SocialMedia.user.UserProfileDto;
import org.springframework.stereotype.Component;

@Component
public class Mapping {
    public UserProfileDto mapUserToUserProfileDto(User user) {
        return UserProfileDto.builder()
                .username(user.getUsername())
                .avatarUrl(user.getAvatarUrl())
                .bio(user.getBio())
                .followersCount(user.getFollowers().size())
                .followingsCount(user.getFollowing().size())
                .build();
    }

    public PostContentDto mapPostToPostContentDto(Post post) {
        return PostContentDto.builder()
                .content(post.getContent())
                .username(post.getUser().getUsername())
                .avatarUrl(post.getUser().getAvatarUrl())
                .build();
    }

}
