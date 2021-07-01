package com.daib.backend.controller;

import com.daib.backend.domain.board.Comment;
import com.daib.backend.domain.board.Post;
import com.daib.backend.dto.CommentRequestDto;
import com.daib.backend.service.CommentService;
import com.daib.backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment")
    public Comment createComment(@RequestBody CommentRequestDto commentRequestDto){
        return commentService.createComment(commentRequestDto);
    }
}
