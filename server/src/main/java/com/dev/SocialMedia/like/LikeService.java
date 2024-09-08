package com.dev.SocialMedia.like;

import com.dev.SocialMedia.common.ApiResponse;
import com.dev.SocialMedia.common.Mapping;
import com.dev.SocialMedia.entity.Like;
import com.dev.SocialMedia.entity.Post;
import com.dev.SocialMedia.entity.User;
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
    private final Mapping mapping;

    public ApiResponse likePost(Long postId, LikeRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new CustomException("user id not found"));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException("post id not found"));
        Like like = new Like();
        like.setUser(user);
        like.setPost(post);
        likeRepository.save(like);
        return new ApiResponse("success", "like post successfully", mapping.mapLikeToLikeDto(like));
    }

    public ApiResponse unlikePost(Long postId, LikeRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new CustomException("user id not found"));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException("post id not found"));
        Like like = new Like();
        like.setUser(user);
        like.setPost(post);
        likeRepository.save(like);
        return new ApiResponse("success", "unlike post successfully", mapping.mapLikeToLikeDto(like));
    }
}
