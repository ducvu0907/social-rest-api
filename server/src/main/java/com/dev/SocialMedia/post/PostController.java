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

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getPost(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPost(id));
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> createPost(@RequestBody CreatePostRequest request) {
        return ResponseEntity.ok(postService.createPost(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Long id) {
        return ResponseEntity.ok(postService.deletePost(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updatePost(@PathVariable Long id, @RequestBody UpdatePostRequest request) {
        return ResponseEntity.ok(postService.updatePost(id, request));
    }
}
