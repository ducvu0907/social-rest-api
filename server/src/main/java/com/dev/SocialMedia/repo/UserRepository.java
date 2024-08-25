package com.dev.SocialMedia.repo;

import com.dev.SocialMedia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
