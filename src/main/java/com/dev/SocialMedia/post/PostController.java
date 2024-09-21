package com.dev.SocialMedia.post;

import com.dev.SocialMedia.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponse> getPost(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getPost(postId));
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> createPost(@RequestBody CreatePostRequest request) {
        return ResponseEntity.ok(postService.createPost(request));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.deletePost(postId));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<ApiResponse> updatePost(@PathVariable Long postId, @RequestBody UpdatePostRequest request) {
        return ResponseEntity.ok(postService.updatePost(postId, request));
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<ApiResponse> getPostComments(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getPostComments(postId));
    }

    @GetMapping("/{postId}/likes")
    public ResponseEntity<ApiResponse> getPostLikes(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getPostLikes(postId));
    }

}
