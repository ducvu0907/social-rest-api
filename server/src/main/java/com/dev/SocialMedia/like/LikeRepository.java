package com.dev.SocialMedia.like;

import com.dev.SocialMedia.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {

}
