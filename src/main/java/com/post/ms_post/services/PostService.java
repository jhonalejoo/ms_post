package com.post.ms_post.services;


import org.springframework.stereotype.Service;

import com.post.ms_post.entities.Like;
import com.post.ms_post.entities.Post;
import com.post.ms_post.repositories.LikeRepository;
import com.post.ms_post.repositories.PostRepository;
import com.post.ms_post.responses.PostResponse;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final LikeRepository likeRepository;


    public PostService(PostRepository postRepository, LikeRepository likeRepository) {
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }
   
  public List<PostResponse> getMyPostsResponse(Long userId) {
    List<Post> posts = postRepository.findByUserId(userId);
    return posts.stream().map(post -> {
        PostResponse response = new PostResponse();
        response.setId(post.getId());
        response.setContent(post.getContent());
        response.setLikesCount(post.getLikes().size()); // Asumiendo la relación en Post
        return response;
    }).toList();
}

public List<PostResponse> getOtherUsersPostsResponse(Long userId) {
    List<Post> posts = postRepository.findByUserIdNot(userId);
    return posts.stream().map(post -> {
        PostResponse response = new PostResponse();
        response.setId(post.getId());
        response.setContent(post.getContent());
        response.setLikesCount(post.getLikes().size());
        return response;
    }).toList();
}

public void likePost(Long postId, Long userId) {
    Post post = postRepository.findById(postId)
            .orElseThrow(() -> new RuntimeException("Post no encontrado"));

    if (likeRepository.existsByPostIdAndUserId(postId, userId)) {
        throw new RuntimeException("Ya diste like a esta publicación");
    }

    Like like = new Like();
    like.setPost(post);
    like.setUserId(userId);
    likeRepository.save(like);
}


}
