package com.post.ms_post.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.post.ms_post.entities.Like;


public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByPostIdAndUserId(Long postId, Long userId);
}
