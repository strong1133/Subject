package com.daib.backend.controller;

import com.daib.backend.domain.board.Post;
import com.daib.backend.dto.PostRequestDto;
import com.daib.backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PostController {
    private PostService postService;


    @GetMapping("/boards")
    public Page<Post> getPosts(@RequestParam int page){
        return postService.getPosts(page);
    }

    @PostMapping("/boards")
    public Post createPost(@RequestBody PostRequestDto postRequestDto){
        return postService.createPost(postRequestDto);
    }

}
