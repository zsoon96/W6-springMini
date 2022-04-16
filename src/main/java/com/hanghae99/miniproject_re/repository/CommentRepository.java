package com.hanghae99.miniproject_re.repository;

import com.hanghae99.miniproject_re.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByHobbyIdOrderByModifiedAt(Integer hobbyId);
}
