package com.subject.springboard.dto;


import com.subject.springboard.board.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostResponseDto {
    private Long maxPageNum;
    List<Post> postList;

    @Builder
    public PostResponseDto(Long maxPageNum, List<Post> postList){
        this.maxPageNum = maxPageNum;
        this.postList = postList;
    }
}
