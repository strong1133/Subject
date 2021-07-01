package com.daib.backend.service;

import com.daib.backend.config.security.UserDetailsImpl;
import com.daib.backend.domain.board.Comment;
import com.daib.backend.domain.board.Post;
import com.daib.backend.domain.board.User;
import com.daib.backend.dto.*;
import com.daib.backend.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostService postService;
    private final UserService userService;

    // Id 값에 해당 하는 Comment 반환 매서드
    public Comment findCommentById(Long id){
        return commentRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("해당 댓글이 없습니다.")
        );
    }

    //Comment 작성
    @Transactional
    public Comment createComment(CommentRequestDto commentRequestDto){
        Post post = postService.findPostById(commentRequestDto.getPostId());
        Comment comment = new Comment(commentRequestDto, post);
        commentRepository.save(comment);
        return comment;
    }

    //전체 Comment 반환 -> 페이징
    @Transactional
    public CommentResponseDto getComments(int page, Long postId) {
        Page<Comment> commentPage = commentRepository.findAllByPost_Id(PageRequest.of(
                page - 1, 10, Sort.by(Sort.Direction.DESC, "createdAt")), postId);
        Long maxPageNum =  maxPageNum();
        return new CommentResponseDto(maxPageNum, commentPage.getContent());
    }

    // 총 페이지 수 반환
    public Long maxPageNum() {
        int nums = (int) commentRepository.count();
        int maxPageNum =1;
        if (nums % 10 == 0){
            maxPageNum = nums/10;
        }else{
            maxPageNum = (nums/10) +1;
        }
        return (long) maxPageNum;
    }

    // 특정 Comment 수정
    @Transactional
    public String updateComment(Long id, CommentUpdateDto commentUpdateDto, UserDetailsImpl userDetails) {
        //User 정보
        User user = userService.getUser(userDetails);

        Comment comment = findCommentById(id);

        if(!comment.getWriter().equals(user.getUsername())){
            throw new IllegalArgumentException("본인의 댓글만 수정이 가능합니다.");
        }

        comment.updateComment(commentUpdateDto);
        System.out.println(comment.getId()+" 번 댓글 수정 완료");
        return "success";
    }

    // 특정 Comment 삭제
    @Transactional
    public String deleteComment(Long id, UserDetailsImpl userDetails) {
        //User 정보
        User user = userService.getUser(userDetails);

        Comment comment = findCommentById(id);

        if(!comment.getWriter().equals(user.getUsername())){
            throw new IllegalArgumentException("본인의 댓글만 삭제 가능합니다.");
        }

        comment.deleteComment();
        System.out.println(comment.getId()+" 번 댓글 삭제 완료");
        return "success";
    }


}
