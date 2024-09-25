package com.dev.SocialMedia.comment;

import com.dev.SocialMedia.common.ApiResponse;
import com.dev.SocialMedia.common.Mapper;
import com.dev.SocialMedia.post.Post;
import com.dev.SocialMedia.user.User;
import com.dev.SocialMedia.exception.CustomException;
import com.dev.SocialMedia.post.PostRepository;
import com.dev.SocialMedia.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final Mapper mapper;

    public ApiResponse commentOnPost(Long postId, CreateCommentRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new CustomException("user id not found"));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException("post id not found"));
        Comment comment = Comment.builder()
                .content(request.getContent())
                .post(post)
                .user(user)
                .parent(null)
                .children(new ArrayList<>())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        commentRepository.save(comment);
        return new ApiResponse("success", "comment on post successfully", mapper.mapCommentToCommentDetailsDto(comment));
    }

    public ApiResponse getComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException("comment id not found"));
        return new ApiResponse("success", "get comment successfully", mapper.mapCommentToCommentDetailsDto(comment));
    }

    public ApiResponse updateComment(Long commentId, UpdateCommentRequest request) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException("comment id not found"));
        comment.setContent(request.getContent());
        comment.setUpdatedAt(LocalDateTime.now());
        commentRepository.save(comment);
        return new ApiResponse("success", "update comment successfully", mapper.mapCommentToCommentDetailsDto(comment));
    }

    // TODO: delete the corresponding notification object in the db
    public ApiResponse deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException("comment id not found"));
        commentRepository.delete(comment);
        return new ApiResponse("success", "delete comment successfully", null);
    }

    public ApiResponse replyToComment(Long postId, Long commentId, CreateCommentRequest request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException("post id not found"));
        User user = userRepository.findById(postId)
                .orElseThrow(() -> new CustomException("user id not found"));
        Comment parentComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException("comment id not found"));
        Comment comment = Comment.builder()
                .content(request.getContent())
                .post(post)
                .user(user)
                .parent(parentComment)
                .children(new ArrayList<>())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        commentRepository.save(comment);
        return new ApiResponse("success", "reply to comment successfully", mapper.mapCommentToCommentDetailsDto(comment));
    }
}
