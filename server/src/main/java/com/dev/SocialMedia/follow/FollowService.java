package com.dev.SocialMedia.follow;

import com.dev.SocialMedia.common.ApiResponse;
import com.dev.SocialMedia.common.Mapping;
import com.dev.SocialMedia.user.User;
import com.dev.SocialMedia.exception.CustomException;
import com.dev.SocialMedia.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final Mapping mapping;

    public ApiResponse followUser(Long followerId, FollowUserRequest request) {
        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new CustomException("follower id not found "));
        User followed = userRepository.findById(request.getFollowedUserId())
                .orElseThrow(() -> new CustomException("followed id not found"));
        Follow follow = Follow.builder()
                .follower(follower)
                .followed(followed)
                .build();
        followRepository.save(follow);
        return new ApiResponse("success", "follow user successfully", mapping.mapFollowToFollowDto(follow));
    }

    public ApiResponse unfollowUser(Long followerId, FollowUserRequest request) {
        Follow follow = followRepository.findByFollowerIdAndFollowedId(followerId, request.getFollowedUserId())
                .orElseThrow(() -> new CustomException("follow not found"));
        followRepository.delete(follow);
        return new ApiResponse("success", "unfollow user successfully", mapping.mapFollowToFollowDto(follow));
    }

}
