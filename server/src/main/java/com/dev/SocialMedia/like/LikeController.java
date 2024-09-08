package com.dev.SocialMedia.like;

import com.dev.SocialMedia.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/{postId}")
    public ResponseEntity<ApiResponse> likePost(@PathVariable Long postId, @RequestBody LikeRequest request) {
        return ResponseEntity.ok(likeService.likePost(postId, request));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse> unlikePost(@PathVariable Long postId, @RequestBody LikeRequest request) {
        return ResponseEntity.ok(likeService.unlikePost(postId, request));
    }
}
