package com.dev.SocialMedia.like;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findLikeByUserIdAndPostId(Long userId, Long postId);
}
