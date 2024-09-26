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
    public ResponseEntity<ApiResponse> likePost(@PathVariable Long postId) {
        return ResponseEntity.ok(likeService.likePost(postId));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse> unlikePost(@PathVariable Long postId) {
        return ResponseEntity.ok(likeService.unlikePost(postId));
    }

    @PostMapping("/{commentId}")
    public ResponseEntity<ApiResponse> likeComment(@PathVariable Long commentId) {
        return ResponseEntity.ok(likeService.likeComment(commentId));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponse> unlikeComment(@PathVariable Long commentId) {
        return ResponseEntity.ok(likeService.unlikeComment(commentId));
    }
}
