package com.subject.springboard.service;

import com.subject.springboard.board.Post;
import com.subject.springboard.board.User;
import com.subject.springboard.dto.PostRequestDto;
import com.subject.springboard.dto.PostResponseDto;
import com.subject.springboard.dto.PostSaveDto;
import com.subject.springboard.dto.PostUpdateDto;
import com.subject.springboard.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    // Id 값에 해당 하는 Post 반환 매서드
    public Post findPostById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다."));
    }

    // Post 작성
    @Transactional
    public Post createPost(PostRequestDto postRequestDto, Authentication authentication) {
        User user = userService.curUserInfo(authentication);
        PostSaveDto postSaveDto = new PostSaveDto(postRequestDto, user.getUsername());
        Post post = new Post(postSaveDto);
        System.out.println(post.getTitle() + " 게시물 작성 완료");
        return postRepository.save(post);
    }

    //전체 Post 반환 -> 페이징
    @Transactional
    public PostResponseDto getPosts(int page) {
        Page<Post> postPage = postRepository.findAllByDeleteCheck(PageRequest.of(
                page - 1, 10, Sort.by(Sort.Direction.DESC, "createdAt")),
                false);
        Long maxPageNum = pagination.maxPageNum();
        Long starPageNum =pagination.starPageNum(page);
        boolean prevBtn = pagination.prevBtn(page);
        boolean nextBtn = pagination.nextBtn(page);
        return new PostResponseDto(maxPageNum,  starPageNum, postPage.getContent(), prevBtn, nextBtn);
    }



    // 특정 Post 수정
    @Transactional
    public String updatePost(Long id, PostUpdateDto postUpdateDto, Authentication authentication ) {
        //User 정보
        User user = userService.curUserInfo(authentication);

        Post post = findPostById(id);

        if(!post.getWriter().equals(user.getUsername())){
            throw new IllegalArgumentException("본인의 글만 수정이 가능합니다.");
        }

        post.updatePost(postUpdateDto);
        System.out.println(post.getId()+" 번 게시물 수정 완료");
        return "success";
    }

    // 특정 Post 삭제
    @Transactional
    public String deletePost(Long id, Authentication authentication) {
        //User 정보
        User user = userService.curUserInfo(authentication);

        Post post = findPostById(id);

        if(!post.getWriter().equals(user.getUsername())){
            throw new IllegalArgumentException("본인의 글만 삭제 가능합니다.");
        }

        post.deletePost();
        System.out.println(post.getId()+" 번 게시물 삭제 완료");
        return "success";
    }

    public Post getPostDetails(Long id){
        return findPostById(id);
    }

}
