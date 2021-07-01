package com.daib.backend.controller;

import com.daib.backend.domain.board.Post;
import com.daib.backend.dto.PostRequestDto;
import com.daib.backend.dto.PostResponseDto;
import com.daib.backend.dto.PostUpdateDto;
import com.daib.backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public String updatePost(@PathVariable Long id, @RequestBody PostUpdateDto postUpdateDto){
        return postService.updatePost(id, postUpdateDto);
    }

    //Post Delete
    @DeleteMapping("/boards/{id}")
    public String deletePost(@PathVariable Long id){
        return postService.deletePost(id);
    }

}
