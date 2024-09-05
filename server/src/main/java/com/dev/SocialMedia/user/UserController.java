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

    @GetMapping("/{username}")
    public ResponseEntity<ApiResponse> getUserProfile(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserProfile(username));
    }

    @PostMapping("/{id}")
    public ResponseEntity<ApiResponse> updateUserProfile(@PathVariable Long id, @RequestBody UpdateProfileRequest request) {
        return ResponseEntity.ok(userService.updateUserProfile(id, request));
    }

}
