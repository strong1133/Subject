package com.subject.springboard.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostSaveDto {

    private String title;

    private String content;

    private String writer;

    public PostSaveDto(PostRequestDto postRequestDto, String username){
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
        this.writer = username;
    }
}
