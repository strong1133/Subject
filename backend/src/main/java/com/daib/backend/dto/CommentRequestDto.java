package com.daib.backend.dto;

import com.daib.backend.domain.board.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {
    private Long postId;
    private String writer;
    private String content;
}
