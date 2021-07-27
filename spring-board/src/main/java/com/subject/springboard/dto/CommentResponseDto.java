package com.subject.springboard.dto;


import com.subject.springboard.board.Comment;
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
