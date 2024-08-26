package com.dev.SocialMedia.repository;

import com.dev.SocialMedia.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
