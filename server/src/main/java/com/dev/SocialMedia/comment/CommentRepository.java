package com.dev.SocialMedia.comment;

import com.dev.SocialMedia.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
