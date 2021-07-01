package com.daib.backend.controller;

import com.daib.backend.config.security.UserDetailsImpl;
import com.daib.backend.domain.board.Post;
import com.daib.backend.dto.PostRequestDto;
import com.daib.backend.dto.PostResponseDto;
import com.daib.backend.dto.PostUpdateDto;
import com.daib.backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Post createPost(@RequestBody PostRequestDto postRequestDto){
        return postService.createPost(postRequestDto);
    }

    //Post Update
    @PutMapping("/boards/{id}")
    public String updatePost(@PathVariable Long id, @RequestBody PostUpdateDto postUpdateDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.updatePost(id, postUpdateDto, userDetails);
    }

    //Post Delete
    @DeleteMapping("/boards/{id}")
    public String deletePost(@PathVariable Long id,  @AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.deletePost(id, userDetails);
    }

    //Post Read -> Details
    @GetMapping("/boards/{id}")
    public Post getPostDetails(@PathVariable Long id){
        return postService.getPostDetails(id);
    }
}
