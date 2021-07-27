package com.subject.springboard.board;


import com.subject.springboard.dto.PostSaveDto;
import com.subject.springboard.dto.PostUpdateDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "post")
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private boolean deleteCheck;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<Comment> commentList = new ArrayList<>();

    // PostRequestDto 생성자
    public Post (PostSaveDto postSaveDto){
        this.title = postSaveDto.getTitle();
        this.content = postSaveDto.getContent();
        this.writer = postSaveDto.getWriter();
        this.deleteCheck = false;
    }

    // post 수정 매서드
    public void updatePost(PostUpdateDto postUpdateDto){
        this.title = postUpdateDto.getTitle();
        this.content = postUpdateDto.getContent();
    }

    // post 삭제 메서드
    public void deletePost(){
        this.content = "삭제된 게시물 입니다.";
        this.deleteCheck = true;
    }
}
