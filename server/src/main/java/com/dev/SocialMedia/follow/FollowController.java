package com.dev.SocialMedia.follow;

import com.dev.SocialMedia.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/follows")
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;

    @PostMapping("/{userId}")
    public ResponseEntity<ApiResponse> followUser(@PathVariable Long userId, @RequestBody FollowUserRequest request) {
        return ResponseEntity.ok(followService.followUser(userId, request));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> unfollowUser(@PathVariable Long userId, @RequestBody FollowUserRequest request) {
        return ResponseEntity.ok(followService.unfollowUser(userId, request));
    }

}
