package com.daib.backend.controller;

import com.daib.backend.config.security.UserDetailsImpl;
import com.daib.backend.domain.board.Comment;
import com.daib.backend.domain.board.Post;
import com.daib.backend.dto.*;
import com.daib.backend.service.CommentService;
import com.daib.backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //Comment Read
    @GetMapping("/comment")
    public CommentResponseDto getPosts(@RequestParam Long postId, int page){
        return commentService.getComments(page, postId);
    }


    //Comment Create
    @PostMapping("/comment")
    public Comment createComment(@RequestBody CommentRequestDto commentRequestDto){
        return commentService.createComment(commentRequestDto);
    }

    //Comment Update
    @PutMapping("/comment/{id}")
    public String updateComment(@PathVariable Long id, @RequestBody CommentUpdateDto commentUpdateDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.updateComment(id, commentUpdateDto, userDetails);
    }

    //Comment Delete
    @DeleteMapping("/comment/{id}")
    public String deleteComment(@PathVariable Long id,  @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.deleteComment(id, userDetails);
    }
}
