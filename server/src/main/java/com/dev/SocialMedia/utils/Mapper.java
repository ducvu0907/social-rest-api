package com.dev.SocialMedia.utils;

import com.dev.SocialMedia.dto.*;
import com.dev.SocialMedia.entity.*;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {

    public static UserDto mapUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setAvatarUrl(user.getAvatarUrl());
        userDto.setBio(user.getBio());
        userDto.setFollowersCount(user.getFollowers().size());
        userDto.setFollowingsCount(user.getFollowing().size());
        return userDto;
    }

    public static PostDto mapPostToPostDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setContent(post.getContent());
        postDto.setUser(mapUserToUserDto(post.getUser()));
        return postDto;
    }

    public static CommentDto mapCommentToCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setContent(comment.getContent());
        commentDto.setUser(mapUserToUserDto(comment.getUser()));
        commentDto.setCreatedAt(comment.getCreatedAt());
        return commentDto;
    }

    public static LikeDto mapLikeToLikeDto(Like like) {
        LikeDto likeDto = new LikeDto();
        likeDto.setId(like.getId());
        likeDto.setUser(mapUserToUserDto(like.getUser()));
        likeDto.setPost(mapPostToPostDto(like.getPost()));
        likeDto.setCreatedAt(like.getCreatedAt());
        return likeDto;
    }

    public static FollowDto mapFollowToFollowDto(Follow follow) {
        FollowDto followDto = new FollowDto();
        followDto.setId(follow.getId());
        followDto.setFollower(mapUserToUserDto(follow.getFollower()));
        followDto.setFollowed(mapUserToUserDto(follow.getFollowed()));
        return followDto;
    }

    public static List<UserDto> mapUserListToUserDtoList(List<User> users) {
        return users.stream().map(Mapper::mapUserToUserDto).collect(Collectors.toList());
    }

    public static List<PostDto> mapPostListToPostDtoList(List<Post> posts) {
        return posts.stream().map(Mapper::mapPostToPostDto).collect(Collectors.toList());
    }

    public static List<CommentDto> mapCommentListToCommentDtoList(List<Comment> comments) {
        return comments.stream().map(Mapper::mapCommentToCommentDto).collect(Collectors.toList());
    }

    public static List<LikeDto> mapLikeListToLikeDtoList(List<Like> likes) {
        return likes.stream().map(Mapper::mapLikeToLikeDto).collect(Collectors.toList());
    }
}
