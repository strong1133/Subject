package com.daib.backend.service;

import com.daib.backend.config.security.UserDetailsImpl;
import com.daib.backend.domain.board.Post;
import com.daib.backend.domain.board.User;
import com.daib.backend.dto.PostRequestDto;
import com.daib.backend.dto.PostResponseDto;
import com.daib.backend.dto.PostUpdateDto;
import com.daib.backend.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    public Post findPostById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다."));
    }



    // Post 작성
    @Transactional
    public Post createPost(PostRequestDto postRequestDto) {
        Post post = new Post(postRequestDto);
        System.out.println(post.getTitle() + " 게시물 작성 완료");
        return postRepository.save(post);
    }

    //전체 Post 반환 -> 페이징
    @Transactional
    public PostResponseDto getPosts(int page) {
        Page<Post> postPage = postRepository.findAllByDeleteCheck(PageRequest.of(
                page - 1, 10, Sort.by(Sort.Direction.DESC, "createdAt")),
                false);
        Long maxPageNum =  maxPageNum();
        return new PostResponseDto(maxPageNum, postPage.getContent());
    }

    // 총 페이지 수 반환
    public Long maxPageNum() {
        int nums = (int) postRepository.count();
        int maxPageNum =1;
        if (nums % 10 == 0){
            maxPageNum = nums/10;
        }else{
            maxPageNum = (nums/10) +1;
        }
        return (long) maxPageNum;
    }

    // 특정 Post 수정
    @Transactional
    public String updatePost(Long id, PostUpdateDto postUpdateDto, UserDetailsImpl userDetails) {
        //User 정보
        User user = userService.getUser(userDetails);

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
    public String deletePost(Long id, UserDetailsImpl userDetails) {
        //User 정보
        User user = userService.getUser(userDetails);

        Post post = findPostById(id);

        if(!post.getWriter().equals(user.getUsername())){
            throw new IllegalArgumentException("본인의 글만 삭제 가능합니다.");
        }

        post.deletePost();
        System.out.println(post.getId()+" 번 게시물 삭제 완료");
        return "success";
    }

}
