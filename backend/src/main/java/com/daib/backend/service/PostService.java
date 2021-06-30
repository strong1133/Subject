package com.daib.backend.service;

import com.daib.backend.domain.board.Post;
import com.daib.backend.dto.PostRequestDto;
import com.daib.backend.dto.PostUpdateDto;
import com.daib.backend.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post findPostById(Long id){
        return postRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시물이 없습니다."));
    }

    // Post 작성
    @Transactional
    public Post createPost(PostRequestDto postRequestDto){
            Post post = new Post(postRequestDto);
            System.out.println(post.getId() +" 번 게시물 작성 완료");
            return postRepository.save(post);
    }

    //전체 Post 반환 -> 페이징
    @Transactional
    public Page<Post> getPosts(int page){
        return postRepository.findAllByDeleteCheck(PageRequest.of(page -1 , 10, Sort.by(Sort.Direction.DESC, "createdAt")), false);
    }

    // 총 페이지 수 반환
    public Long maxPageNum(){
        return postRepository.count();
    }

    // 특정 Post 수정
    @Transactional
    public String updatePost(Long id, PostUpdateDto postUpdateDto){
        Post post = findPostById(id);
        post.updatePost(postUpdateDto);
        return "success";
    }

    // 특정 Post 삭제
    @Transactional
    public String deletePost(Long id){
        Post post =  findPostById(id);
        post.setContent(null);
        post.setDeleteCheck(true);
        return "success";
    }

}
