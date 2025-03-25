package com.post.ms_post.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.post.ms_post.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserId(Long userId);
    List<Post> findByUserIdNot(Long userId);
}