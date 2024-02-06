package com.sparta.schedules.repository;

import com.sparta.schedules.dto.CommentResponseDto;
import com.sparta.schedules.entity.Comment;
import com.sparta.schedules.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<CommentResponseDto> findAllBySchedule(Schedule sc);

    Comment findBySchedule(Schedule schedule);
}
