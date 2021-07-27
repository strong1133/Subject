package com.subject.springboard.controller;


import com.subject.springboard.board.Post;
import com.subject.springboard.dto.PostRequestDto;
import com.subject.springboard.dto.PostResponseDto;
import com.subject.springboard.dto.PostUpdateDto;
import com.subject.springboard.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    //Post Read
    @GetMapping("/boards")
    public PostResponseDto getPosts(@RequestParam int page){
        return postService.getPosts(page);
    }

    //Post Create
    @PostMapping("/boards")
    public Post createPost(@RequestBody PostRequestDto postRequestDto, Authentication authentication){
        return postService.createPost(postRequestDto, authentication);
    }

    //Post Update
    @PutMapping("/boards/{id}")
    public String updatePost(@PathVariable Long id, @RequestBody PostUpdateDto postUpdateDto, Authentication authentication){
        return postService.updatePost(id, postUpdateDto, authentication);
    }

    //Post Delete
    @DeleteMapping("/boards/{id}")
    public String deletePost(@PathVariable Long id, Authentication authentication ){
        return postService.deletePost(id, authentication);
    }

    //Post Read -> Details
    @GetMapping("/boards/{id}")
    public Post getPostDetails(@PathVariable Long id){
        return postService.getPostDetails(id);
    }
}
