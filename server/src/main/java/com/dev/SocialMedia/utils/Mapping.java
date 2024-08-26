package com.dev.SocialMedia.utils;

import com.dev.SocialMedia.dto.*;
import com.dev.SocialMedia.entity.*;

public class Mapping {

    public static UserDto mapUserToUserDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setProfilePicture(user.getProfilePicture());
        dto.setBio(user.getBio());
        dto.setFollowersCount(user.getFollowers().size());
        dto.setFollowingsCount(user.getFollowing().size());
        // FIXME: might include other fields later
        return dto;
    }

    public static PostDto mapPostToPostDto(Post post) {

    }

    public static CommentDto mapCommentToCommentDto(Comment comment) {

    }

    public static LikeDto mapLikeToLikeDto(Like like) {

    }

    public static FollowDto mapFollowToFollowDto(Follow follow) {

    }
}
