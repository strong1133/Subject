package com.daib.backend.dto;

import com.daib.backend.domain.board.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Getter
@Setter
public class PostResponseDto {
    private Long maxPageNum;
    List<Post> postList;

    @Builder
    public PostResponseDto(Long maxPageNum,List<Post> postList){
        this.maxPageNum = maxPageNum;
        this.postList = postList;
    }
}
