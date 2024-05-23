package com.sparta.schedules.domain.schedule.service;

import com.sparta.schedules.domain.schedule.dto.ScheduleRequestDto;
import com.sparta.schedules.domain.schedule.dto.ScheduleResponseDto;
import com.sparta.schedules.domain.schedule.entity.Schedule;
import com.sparta.schedules.domain.user.entity.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public interface ScheduleService {
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto, User user);

    //// get
    public List<ScheduleResponseDto> getSchedule(User user);

    ////update
    public Long updateSchedule(Long id, ScheduleRequestDto requestDto, User user);

    ////delete
    public Long deleteSchedule(Long id, User user);

    // Find by ID + user
    public ScheduleResponseDto searchMemo(Long id, User user);
    public List<ScheduleResponseDto> searchMemoDate(LocalDate id, User user);
}
