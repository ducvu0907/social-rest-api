package com.dev.SocialMedia.common;

import com.dev.SocialMedia.comment.Comment;
import com.dev.SocialMedia.comment.CommentDetailsDto;
import com.dev.SocialMedia.follow.Follow;
import com.dev.SocialMedia.follow.FollowDto;
import com.dev.SocialMedia.like.Like;
import com.dev.SocialMedia.like.LikeDto;
import com.dev.SocialMedia.notification.Notification;
import com.dev.SocialMedia.notification.NotificationDto;
import com.dev.SocialMedia.post.Post;
import com.dev.SocialMedia.post.PostDetailsDto;
import com.dev.SocialMedia.user.User;
import com.dev.SocialMedia.user.UserDetailsDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    public UserDetailsDto mapUserToUserDetailsDto(User user) {
        return UserDetailsDto.builder()
                .username(user.getUsername())
                .avatarUrl(user.getAvatarUrl())
                .bio(user.getBio())
                .followersCount(user.getFollowers().size())
                .followingsCount(user.getFollowing().size())
                .build();
    }

    public PostDetailsDto mapPostToPostDetailsDto(Post post) {
        return PostDetailsDto.builder()
                .content(post.getContent())
                .username(post.getUser().getUsername())
                .userAvatarUrl(post.getUser().getAvatarUrl())
                .commentsCount(post.getComments().size())
                .likesCount(post.getLikes().size())
                .build();
    }

    public LikeDto mapLikeToLikeDto(Like like) {
        return LikeDto.builder()
                .postId(like.getPost().getId())
                .username(like.getUser().getUsername())
                .userAvatarUrl(like.getUser().getAvatarUrl())
                .build();
    }

    public CommentDetailsDto mapCommentToCommentDetailsDto(Comment comment) {
        return CommentDetailsDto.builder()
                .username(comment.getUser().getUsername())
                .userAvatarUrl(comment.getUser().getAvatarUrl())
                .postId(comment.getPost().getId())
                .content(comment.getContent())
                .build();
    }

    public FollowDto mapFollowToFollowDto(Follow follow) {
        return FollowDto.builder()
                .followerName(follow.getFollower().getUsername())
                .followerAvatarUrl(follow.getFollower().getAvatarUrl())
                .followedName(follow.getFollowed().getUsername())
                .followedAvatarUrl(follow.getFollowed().getAvatarUrl())
                .build();
    }

    public NotificationDto mapNotificationToNotificationDto(Notification notification) {
        return NotificationDto.builder()
                .type(notification.getType())
                .message(notification.getMessage())
                .contentId(notification.getContentId())
                .timestamp(notification.getTimestamp())
                .build();
    }

    public List<PostDetailsDto> mapListPostToListPostDetailsDto(List<Post> posts) {
        return posts.stream().map(this::mapPostToPostDetailsDto).toList();
    }

    public List<CommentDetailsDto> mapListCommentToListCommentDetailsDto(List<Comment> comments) {
        return comments.stream().map(this::mapCommentToCommentDetailsDto).toList();
    }

    public List<FollowDto> mapListFollowToListFollowDto(List<Follow> follows) {
        return follows.stream().map(this::mapFollowToFollowDto).toList();
    }

    public List<LikeDto> mapListLikeToListLikeDto(List<Like> likes) {
        return likes.stream().map(this::mapLikeToLikeDto).toList();
    }

    public List<NotificationDto> mapListNotificationToListNotificationDto(List<Notification> notifications) {
        return notifications.stream().map(this::mapNotificationToNotificationDto).toList();
    }
}
