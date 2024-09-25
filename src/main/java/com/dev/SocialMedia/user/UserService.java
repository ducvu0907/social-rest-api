package com.dev.SocialMedia.user;

import com.dev.SocialMedia.common.ApiResponse;
import com.dev.SocialMedia.common.FileStorageService;
import com.dev.SocialMedia.common.Mapper;
import com.dev.SocialMedia.common.Util;
import com.dev.SocialMedia.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final FileStorageService fileStorageService;
    private final Mapper mapper;
    private final Util util;

    public ApiResponse getUserDetailsByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("user id not found"));

        return new ApiResponse("success", "get user details successfully", mapper.mapUserToUserDetailsDto(user));
    }

    public ApiResponse getUserDetailsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException("username not found"));

        return new ApiResponse("success", "get user details successfully", mapper.mapUserToUserDetailsDto(user));
    }

    public ApiResponse updateUser(Long userId, MultipartFile avatarFile, UpdateUserRequest request) {
        Long currentUserId = util.getCurrentUserId();
        if (!currentUserId.equals(userId)) {
            throw new CustomException("not authorized to update this profile");
        }

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

        if (avatarFile != null) {
            fileStorageService.init();
            fileStorageService.store(avatarFile);
            user.setAvatarUrl("api/files/avatars" + avatarFile.getOriginalFilename());
        }

        userRepository.save(user);
        return new ApiResponse("success", "update user successfully", mapper.mapUserToUserDetailsDto(user));
    }

    public ApiResponse getUserFollowers(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("user id not found"));

        return new ApiResponse("success", "get user followers successfully", mapper.mapListFollowToListFollowDto(user.getFollowers()));
    }

    public ApiResponse getUserFollowings(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("user id not found"));
        return new ApiResponse("success", "get user followings successfully", mapper.mapListFollowToListFollowDto(user.getFollowing()));
    }

    public ApiResponse getUserPosts(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("user id not found"));
        return new ApiResponse("success", "get user posts successfully", mapper.mapListPostToListPostDetailsDto(user.getPosts()));
    }

    public ApiResponse getUserComments(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("user id not found"));
        return new ApiResponse("success", "get user comments successfully", mapper.mapListCommentToListCommentDetailsDto(user.getComments()));
    }

    public ApiResponse getUserLikes(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("user id not found"));
        return new ApiResponse("success", "get user comments successfully", mapper.mapListLikeToListLikeDto(user.getLikes()));
    }

}
