package com.sparta.schedules.domain.comment.repository;

import com.sparta.schedules.domain.comment.dto.CommentResponseDto;
import com.sparta.schedules.domain.comment.entity.Comment;
import com.sparta.schedules.domain.schedule.entity.Schedule;
import java.util.List;

public interface CommentRepositoryCustom {
    List<CommentResponseDto> findAllBySchedule(Schedule sc);

    Comment findBySchedule(Schedule schedule);
}
