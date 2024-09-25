package com.dev.SocialMedia.like;

import com.dev.SocialMedia.comment.Comment;
import com.dev.SocialMedia.comment.CommentRepository;
import com.dev.SocialMedia.common.ApiResponse;
import com.dev.SocialMedia.common.Mapper;
import com.dev.SocialMedia.notification.NotificationRepository;
import com.dev.SocialMedia.post.Post;
import com.dev.SocialMedia.user.User;
import com.dev.SocialMedia.exception.CustomException;
import com.dev.SocialMedia.post.PostRepository;
import com.dev.SocialMedia.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final NotificationRepository notificationRepository;
    private final Mapper mapper;

    public ApiResponse likePost(Long postId, LikeRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new CustomException("user id not found"));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException("post id not found"));
        Like like = Like.builder()
                .user(user)
                .type("post")
                .post(post)
                .build();
        likeRepository.save(like);
        return new ApiResponse("success", "like post successfully", mapper.mapLikeToLikeDto(like));
    }

    // TODO: unlike post deletes notification in the db
    public ApiResponse unlikePost(Long postId, LikeRequest request) {
        Like like = likeRepository.findLikeByUserIdAndPostId(request.getUserId(), postId)
                .orElseThrow(() -> new CustomException("like not found"));
        likeRepository.delete(like);
        return new ApiResponse("success", "unlike post successfully", null);
    }

    public ApiResponse likeComment(Long commentId, LikeRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new CustomException("user id not found"));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException("comment id not found"));
        Like like = Like.builder()
                .user(user)
                .type("comment")
                .comment(comment)
                .build();
        likeRepository.save(like);
        return new ApiResponse("success", "like comment successfully", mapper.mapLikeToLikeDto(like));
    }

    // TODO: unlike post deletes notification in the db
    public ApiResponse unlikeComment(Long commentId, LikeRequest request) {
        Like like = likeRepository.findLikeByUserIdAndCommentId(request.getUserId(), commentId)
                .orElseThrow(() -> new CustomException("like not found"));
        likeRepository.delete(like);
        return new ApiResponse("success", "unlike comment successfully", null);
    }
}
