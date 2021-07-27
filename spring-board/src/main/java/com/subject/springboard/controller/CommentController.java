package com.subject.springboard.controller;


import com.subject.springboard.board.Comment;
import com.subject.springboard.dto.CommentRequestDto;
import com.subject.springboard.dto.CommentResponseDto;
import com.subject.springboard.dto.CommentUpdateDto;
import com.subject.springboard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
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
    public Comment createComment(@RequestBody CommentRequestDto commentRequestDto, Authentication authentication){
        return commentService.createComment(commentRequestDto, authentication);
    }

    //Comment Update
    @PutMapping("/comment/{id}")
    public String updateComment(@PathVariable Long id, @RequestBody CommentUpdateDto commentUpdateDto, Authentication authentication){
        return commentService.updateComment(id, commentUpdateDto, authentication);
    }

    //Comment Delete
    @DeleteMapping("/comment/{id}")
    public String deleteComment(@PathVariable Long id,  Authentication authentication){
        return commentService.deleteComment(id, authentication);
    }
}
