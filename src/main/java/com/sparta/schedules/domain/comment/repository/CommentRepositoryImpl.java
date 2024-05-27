package com.sparta.schedules.domain.comment.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.schedules.domain.comment.dto.CommentResponseDto;
import com.sparta.schedules.domain.comment.entity.Comment;
import com.sparta.schedules.domain.comment.entity.QComment;
import com.sparta.schedules.domain.schedule.entity.QSchedule;
import com.sparta.schedules.domain.schedule.entity.Schedule;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    public static final QComment comment = new QComment("comment");

    @Override
    public List<CommentResponseDto> findAllBySchedule(Long scId) {
        BooleanExpression predicate = comment.schedule.id.eq(scId);
        return jpaQueryFactory.
            select(
                Projections.constructor(CommentResponseDto.class, comment.comment))
            .from(comment)
            .where(predicate)
            .orderBy(comment.id.desc())
            .fetch();
    }

    @Override
    public Comment findBySchedule(Schedule schedule) {
        return null;
    }
}
