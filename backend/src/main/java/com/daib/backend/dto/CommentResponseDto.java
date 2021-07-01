package com.daib.backend.dto;

import com.daib.backend.domain.board.Comment;
import com.daib.backend.domain.board.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CommentResponseDto {
    private Long maxPageNum;
    List<Comment> commentList;

    @Builder
    public CommentResponseDto(Long maxPageNum, List<Comment> commentList ){
        this.maxPageNum = maxPageNum;
        this.commentList = commentList;
    }
}
