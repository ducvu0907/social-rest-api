package com.dev.SocialMedia.user;

import com.dev.SocialMedia.common.ApiResponse;
import com.dev.SocialMedia.common.Mapping;
import com.dev.SocialMedia.entity.User;
import com.dev.SocialMedia.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final Mapping mapping;

    public ApiResponse getUserDetailsByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("user id not found"));

        return new ApiResponse("success", "get user details successfully", mapping.mapUserToUserDetailsDto(user));
    }

    public ApiResponse getUserDetailsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException("username not found"));

        return new ApiResponse("success", "get user details successfully", mapping.mapUserToUserDetailsDto(user));
    }

    public ApiResponse updateUser(Long userId, UpdateUserRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("user id not found"));
        if (request.getBio() != null) {
            user.setBio(request.getBio());
        }
        if (request.getUsername() != null) {
            if (userRepository.findByUsername(request.getUsername()).isPresent()) {
                throw new CustomException("username already exists");
            }
            user.setUsername(request.getUsername());
        }
        // TODO: update user avatar
        userRepository.save(user);
        return new ApiResponse("success", "update user successfully", mapping.mapUserToUserDetailsDto(user));
    }

    public ApiResponse getUserFollowers(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("user id not found"));

        return new ApiResponse("success", "get user followers successfully", mapping.mapListFollowToListFollowDto(user.getFollowers()));
    }

    public ApiResponse getUserFollowings(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("user id not found"));
        return new ApiResponse("success", "get user followings successfully", mapping.mapListFollowToListFollowDto(user.getFollowing()));
    }

    public ApiResponse getUserPosts(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("user id not found"));
        return new ApiResponse("success", "get user posts successfully", mapping.mapListPostToListPostDetailsDto(user.getPosts()));
    }

    public ApiResponse getUserComments(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("user id not found"));
        return new ApiResponse("success", "get user comments successfully", mapping.mapListCommentToListCommentDetailsDto(user.getComments()));
    }

    public ApiResponse getUserLikes(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("user id not found"));
        return new ApiResponse("success", "get user comments successfully", mapping.mapListLikeToListLikeDto(user.getLikes()));
    }
}
