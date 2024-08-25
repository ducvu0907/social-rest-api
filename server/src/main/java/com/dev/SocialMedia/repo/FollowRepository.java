package com.dev.SocialMedia.repo;

import com.dev.SocialMedia.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
}
