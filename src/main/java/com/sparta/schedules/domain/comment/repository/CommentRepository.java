package com.sparta.schedules.domain.comment.repository;

import com.sparta.schedules.domain.comment.dto.CommentResponseDto;
import com.sparta.schedules.domain.comment.entity.Comment;
import com.sparta.schedules.domain.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long>,CommentRepositoryCustom {

}