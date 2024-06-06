package com.sparta.schedules.domain.comment.repository;

import com.sparta.schedules.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long>,CommentRepositoryCustom {

}
