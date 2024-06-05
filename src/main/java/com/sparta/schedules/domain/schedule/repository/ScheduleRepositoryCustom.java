package com.sparta.schedules.domain.schedule.repository;

import com.sparta.schedules.domain.schedule.dto.ScheduleResponseDto;
import com.sparta.schedules.domain.user.entity.User;
import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepositoryCustom {
    List<ScheduleResponseDto> findAllByDateAndUser(LocalDate date, User user);

    ScheduleResponseDto findByIdAndUser(Long id, User user);

    List<ScheduleResponseDto> findByUser(User user);
}
