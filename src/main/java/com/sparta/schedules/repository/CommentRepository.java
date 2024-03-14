package com.sparta.schedules.repository;

import com.sparta.schedules.dto.CommentResponseDto;
import com.sparta.schedules.entity.Comment;
import com.sparta.schedules.entity.Schedule;
import com.sparta.schedules.repository.projectionInterface.CommentList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<CommentList> findAllBySchedule(Schedule sc);

    @Query("select t from Comment t join fetch t.schedule")
    Comment findBySchedule(Schedule schedule);
}
