package com.daib.backend.domain.board;

import com.daib.backend.dto.CommentRequestDto;
import com.daib.backend.dto.CommentUpdateDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "comment")
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private String writer;

    private String content;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Comment(CommentRequestDto commentRequestDto, Post post ){
        this.post = post;
        this.writer = commentRequestDto.getWriter();
        this.content = commentRequestDto.getContent();
    }

    // Comment 삭제 메서드
    public void updateComment(CommentUpdateDto commentUpdateDto){
        this.content = commentUpdateDto.getContent();
    }

    // Comment 삭제 메서드
    public void deleteComment(){
        this.content = "삭제된 댓글 입니다.";
    }

}
