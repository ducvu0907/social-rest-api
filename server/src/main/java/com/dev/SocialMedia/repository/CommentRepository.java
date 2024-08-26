package com.dev.SocialMedia.repository;

import com.dev.SocialMedia.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
