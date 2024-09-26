package com.dev.SocialMedia.post;

import com.dev.SocialMedia.common.ApiResponse;
import com.dev.SocialMedia.common.Mapper;
import com.dev.SocialMedia.common.Util;
import com.dev.SocialMedia.user.User;
import com.dev.SocialMedia.exception.CustomException;
import com.dev.SocialMedia.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.support.CustomSQLErrorCodesTranslation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final Mapper mapper;
    private final Util util;

    public ApiResponse getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException("post id not found"));
        return new ApiResponse(
                "success",
                "post retrieved successfully",
                mapper.mapPostToPostDetailsDto(post)
        );
    }

    public ApiResponse createPost(CreatePostRequest request) {
        Long currentUserId = util.getCurrentUserId();

        User user = userRepository.findById(currentUserId)
                .orElseThrow(() -> new CustomException("user id not found"));

        Post post = Post.builder()
                .user(user)
                .content(request.getContent())
                .comments(new ArrayList<>())
                .likes(new ArrayList<>())
                .build();

        postRepository.save(post);

        return new ApiResponse(
                "success",
                "post created successfully",
                mapper.mapPostToPostDetailsDto(post)
        );
    }

    public ApiResponse deletePost(Long postId) {
        Long currentUserId = util.getCurrentUserId();

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException("post id not found"));

        if (!currentUserId.equals(post.getUser().getId())) {
            throw new CustomException("not authorized to delete this post");
        }

        postRepository.delete(post);

        return new ApiResponse(
                "success",
                "post deleted successfully",
                null
        );
    }

    public ApiResponse updatePost(Long postId, UpdatePostRequest request) {
        Long currentUserId = util.getCurrentUserId();

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException("post id not found"));

        if (!currentUserId.equals(post.getUser().getId())) {
            throw new CustomException("not authorized to update this post");
        }

        if (request.getContent() != null) {
            post.setContent(request.getContent());
        }

        postRepository.save(post);

        return new ApiResponse(
                "success",
                "post updated successfully",
                mapper.mapPostToPostDetailsDto(post)
        );
    }

    public ApiResponse getPostComments(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException("post id not found"));
        return new ApiResponse(
                "success",
                "get post comments successfully",
                mapper.mapListCommentToListCommentDetailsDto(post.getComments())
        );
    }

    public ApiResponse getPostLikes(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException("post id not found"));
        return new ApiResponse(
                "success",
                "get post likes successfully",
                mapper.mapListLikeToListLikeDto(post.getLikes())
        );
    }
}
