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

    public ApiResponse getUserProfile(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException("username not found"));

        return new ApiResponse("success", "user profile retrieved successfully", mapping.mapUserToUserProfileDto(user));
    }

    public ApiResponse updateUserProfile(Long id, UpdateProfileRequest request) {
        User user = userRepository.findById(id)
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
        return new ApiResponse("success", "user profile update successfully", mapping.mapUserToUserProfileDto(user));
    }
}
