package com.dev.SocialMedia.follow;

import com.dev.SocialMedia.common.ApiResponse;
import com.dev.SocialMedia.common.AuthUtil;
import com.dev.SocialMedia.common.Mapper;
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
    private final Mapper mapper;
    private final AuthUtil authUtil;

    public ApiResponse followUser(Long followerId, FollowUserRequest request) {
        Long userId = authUtil.getCurrentUserId();
        if (!userId.equals(followerId)) {
            throw new CustomException("not authorized to follow user");
        }
        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new CustomException("follower id not found "));

        User followed = userRepository.findById(request.getFollowedUserId())
                .orElseThrow(() -> new CustomException("followed id not found"));

        Follow follow = Follow.builder()
                .follower(follower)
                .followed(followed)
                .build();

        followRepository.save(follow);

        return new ApiResponse(
                "success",
                "follow user successfully",
                mapper.mapFollowToFollowDto(follow)
        );
    }

    // TODO: delete the corresponding notification
    public ApiResponse unfollowUser(Long followerId, FollowUserRequest request) {
        Long userId = authUtil.getCurrentUserId();
        if (!userId.equals(followerId)) {
            throw new CustomException("not authorized to unfollow user");
        }

        Follow follow = followRepository.findByFollowerIdAndFollowedId(followerId, request.getFollowedUserId())
                .orElseThrow(() -> new CustomException("follow not found"));
        followRepository.delete(follow);

        return new ApiResponse(
                "success",
                "unfollow user successfully",
                mapper.mapFollowToFollowDto(follow)
        );
    }

}
