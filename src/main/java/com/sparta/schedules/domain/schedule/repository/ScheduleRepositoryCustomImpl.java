package com.sparta.schedules.domain.schedule.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.schedules.domain.schedule.dto.ScheduleResponseDto;
import com.sparta.schedules.domain.schedule.entity.QSchedule;
import com.sparta.schedules.domain.schedule.entity.Schedule;
import com.sparta.schedules.domain.user.entity.User;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ScheduleRepositoryCustomImpl implements ScheduleRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    private final QSchedule schedule = QSchedule.schedule;

    @Override
    public List<ScheduleResponseDto> findAllByDateAndUser(LocalDate date, User user) {
        BooleanExpression predicate = schedule.date.eq(date).and(schedule.user.id.eq(user.getId()));

        return jpaQueryFactory
            .select(Projections.fields(ScheduleResponseDto.class, schedule.contents,schedule.date))
            .from(schedule)
            .where(predicate)
            .fetch();
    }

    @Override
    public ScheduleResponseDto findByIdAndUser(Long id, User user) {
        BooleanExpression predicate = schedule.id.eq(id).and(schedule.user.id.eq(user.getId()));

        return jpaQueryFactory
            .select(Projections.fields(ScheduleResponseDto.class, schedule.contents,schedule.date))
            .from(schedule)
            .where(predicate)
            .fetchOne();
    }

    @Override
    public List<ScheduleResponseDto> findByUser(User user) {
        BooleanExpression predicate = schedule.user.username.eq(user.getUsername());

        return jpaQueryFactory
            .select(Projections.fields(ScheduleResponseDto.class, schedule.contents,schedule.date))
            .from(schedule)
            .where(predicate)
            .fetch();
    }
}
