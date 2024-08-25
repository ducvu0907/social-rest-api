package com.dev.SocialMedia.repo;

import com.dev.SocialMedia.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
