package com.post.ms_post.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.post.ms_post.entities.Post;
import com.post.ms_post.responses.PostResponse;
import com.post.ms_post.services.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        return ResponseEntity.ok(postService.createPost(post));
    }

    @GetMapping("/my-posts")
    public ResponseEntity<List<PostResponse>> getMyPosts() {
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<PostResponse> myPosts = postService.getMyPostsResponse(userId);
        return ResponseEntity.ok(myPosts);
    }

    @GetMapping("/other-posts")
    public ResponseEntity<List<PostResponse>> getOtherUsersPosts() {
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<PostResponse> otherPosts = postService.getOtherUsersPostsResponse(userId);
        return ResponseEntity.ok(otherPosts);
    }

    @PostMapping("/{postId}/like")
    public ResponseEntity<String> likePost(@PathVariable Long postId) {
    Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    postService.likePost(postId, userId);
    return ResponseEntity.ok("Like agregado");
}


}