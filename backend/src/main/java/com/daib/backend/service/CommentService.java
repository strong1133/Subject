package com.daib.backend.service;

import com.daib.backend.domain.board.Comment;
import com.daib.backend.domain.board.Post;
import com.daib.backend.dto.CommentRequestDto;
import com.daib.backend.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostService postService;

    @Transactional
    public Comment createComment(CommentRequestDto commentRequestDto){
        Post post = postService.findPostById(commentRequestDto.getPostId());
        Comment comment = new Comment(commentRequestDto, post);
        commentRepository.save(comment);
        return comment;
    }

}
