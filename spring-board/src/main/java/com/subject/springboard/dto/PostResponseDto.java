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
    private Long starPageNum;
    List<Post> postList;
    boolean prevBtn;
    boolean nextBtn;

    @Builder
    public PostResponseDto(Long maxPageNum, Long starPageNum, List<Post> postList, boolean prevBtn, boolean nextBtn){
        this.maxPageNum = maxPageNum;
        this.starPageNum = starPageNum;
        this.postList = postList;
        this.prevBtn = prevBtn;
        this.nextBtn = nextBtn;
    }
}

