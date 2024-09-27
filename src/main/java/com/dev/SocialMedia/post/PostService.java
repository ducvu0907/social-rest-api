package com.dev.SocialMedia.post;

import com.dev.SocialMedia.common.ApiResponse;
import com.dev.SocialMedia.common.AuthUtil;
import com.dev.SocialMedia.common.Mapper;
import com.dev.SocialMedia.user.User;
import com.dev.SocialMedia.exception.CustomException;
import com.dev.SocialMedia.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final Mapper mapper;
    private final AuthUtil authUtil;
    private final RedisTemplate<String, ApiResponse> redisTemplate;

    // test cache response with redis
    public ApiResponse getPost(Long postId) {
        Long userId = authUtil.getCurrentUserId();
        String cachedKey = postId + "_" + userId;
        ApiResponse cachedResponse = redisTemplate.opsForValue().get(cachedKey);

        if (cachedResponse != null) {
            return cachedResponse;
        }

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException("post id not found"));

        ApiResponse response = new ApiResponse(
                "success",
                "post retrieved successfully",
                mapper.mapPostToPostDetailsDto(post)
        );

        redisTemplate.opsForValue().set(cachedKey, response); // store in cache
        redisTemplate.expire(cachedKey, 3600, TimeUnit.SECONDS);

        return response;
    }

    public ApiResponse createPost(CreatePostRequest request) {
        Long currentUserId = authUtil.getCurrentUserId();

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
        Long currentUserId = authUtil.getCurrentUserId();

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
        Long currentUserId = authUtil.getCurrentUserId();

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
