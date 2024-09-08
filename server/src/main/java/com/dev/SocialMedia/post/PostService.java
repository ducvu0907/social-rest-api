package com.dev.SocialMedia.post;

import com.dev.SocialMedia.common.ApiResponse;
import com.dev.SocialMedia.common.Mapping;
import com.dev.SocialMedia.entity.Post;
import com.dev.SocialMedia.entity.User;
import com.dev.SocialMedia.exception.CustomException;
import com.dev.SocialMedia.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final Mapping mapping;

    public ApiResponse getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException("post id not found"));
        return new ApiResponse("success", "post retrieved successfully", mapping.mapPostToPostDetailsDto(post));
    }

    public ApiResponse createPost(CreatePostRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new CustomException("user id not found"));
        Post post = new Post();
        post.setUser(user);
        post.setContent(request.getContent());
        post.setComments(new ArrayList<>());
        post.setLikes(new ArrayList<>());
        postRepository.save(post);
        return new ApiResponse("success", "post created successfully", mapping.mapPostToPostDetailsDto(post));
    }

    public ApiResponse deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException("post id not found"));
        postRepository.delete(post);
        return new ApiResponse("success", "post deleted successfully", null);
    }

    public ApiResponse updatePost(Long postId, UpdatePostRequest request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException("post id not found"));
        if (request.getContent() != null) {
            post.setContent(request.getContent());
        }
        postRepository.save(post);
        return new ApiResponse("success", "post updated successfully", mapping.mapPostToPostDetailsDto(post));
    }

    public ApiResponse getPostComments(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException("post id not found"));
        return new ApiResponse("success", "get post comments successfully", mapping.mapListCommentToListCommentDetailsDto(post.getComments()));
    }

    public ApiResponse getPostLikes(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException("post id not found"));
        return new ApiResponse("success", "get post likes successfully", mapping.mapListLikeToListLikeDto(post.getLikes()));
    }
}
