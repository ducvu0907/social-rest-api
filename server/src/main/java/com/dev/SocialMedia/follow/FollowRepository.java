package com.dev.SocialMedia.follow;

import com.dev.SocialMedia.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {

}
