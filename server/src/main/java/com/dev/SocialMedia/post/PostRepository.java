package com.dev.SocialMedia.post;

import com.dev.SocialMedia.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
