package com.post.ms_post.services;


import org.springframework.stereotype.Service;

import com.post.ms_post.entities.Post;
import com.post.ms_post.repositories.PostRepository;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }
   
    public List<Post> getPostsByUser(Long userId) {
        return postRepository.findByUserId(userId);
    }
}
