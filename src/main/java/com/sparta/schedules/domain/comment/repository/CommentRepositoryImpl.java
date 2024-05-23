package com.sparta.schedules.domain.comment.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.schedules.domain.comment.dto.CommentResponseDto;
import com.sparta.schedules.domain.comment.entity.Comment;
import com.sparta.schedules.domain.schedule.entity.Schedule;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<CommentResponseDto> findAllBySchedule(Schedule sc) {
        return null;
    }

    @Override
    public Comment findBySchedule(Schedule schedule) {
        return null;
    }
}
