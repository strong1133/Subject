package com.daib.backend.repository;

import com.daib.backend.domain.board.Comment;
import com.daib.backend.domain.board.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByPost_Id(Pageable PageRequest, Long id);
}
