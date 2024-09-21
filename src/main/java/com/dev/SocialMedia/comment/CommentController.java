package com.dev.SocialMedia.comment;

import com.dev.SocialMedia.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{postId}")
    public ResponseEntity<ApiResponse> commentOnPost(@PathVariable Long postId, @RequestBody CreateCommentRequest request) {
        return ResponseEntity.ok(commentService.commentOnPost(postId, request));
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<ApiResponse> getCommentDetails(@PathVariable Long commentId) {
        return ResponseEntity.ok(commentService.getComment(commentId));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<ApiResponse> updateComment(@PathVariable Long commentId, @RequestBody UpdateCommentRequest request) {
        return ResponseEntity.ok(commentService.updateComment(commentId, request));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Long commentId) {
        return ResponseEntity.ok(commentService.deleteComment(commentId));
    }

    @PostMapping("/{postId}/{commentId}")
    public ResponseEntity<ApiResponse> replyToComment(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId, @RequestBody CreateCommentRequest request) {
        return ResponseEntity.ok(commentService.replyToComment(postId, commentId, request));
    }
}
