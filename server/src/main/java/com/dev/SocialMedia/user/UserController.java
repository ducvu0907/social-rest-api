package com.dev.SocialMedia.user;

import com.dev.SocialMedia.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse> getUserProfileById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserDetailsByUserId(userId));
    }

    // TODO: might change to this instead of user id
    // @GetMapping("/{username}")
    // public ResponseEntity<ApiResponse> getUserProfileByUsername(@PathVariable String username) {
    //     return ResponseEntity.ok(userService.getUserDetailsByUsername(username));
    // }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse> updateUserProfile(@PathVariable Long userId, @RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok(userService.updateUser(userId, request));
    }


    @GetMapping("/{userId}/followers")
    public ResponseEntity<ApiResponse> getUserFollowers(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserFollowers(userId));
    }

    @GetMapping("/{userId}/followings")
    public ResponseEntity<ApiResponse> getUserFollowings(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserFollowings(userId));
    }

    @GetMapping("/{userId}/posts")
    public ResponseEntity<ApiResponse> getUserPosts(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserPosts(userId));
    }

    @GetMapping("/{userId}/comments")
    public ResponseEntity<ApiResponse> getUserComments(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserComments(userId));
    }


    @GetMapping("/{userId}/likes")
    public ResponseEntity<ApiResponse> getUserLikes(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserLikes(userId));
    }
}
