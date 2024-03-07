package com.sparta.schedules.servicetest;

import com.sparta.schedules.dto.ScheduleRequestDto;
import com.sparta.schedules.dto.ScheduleResponseDto;
import com.sparta.schedules.entity.Schedule;
import com.sparta.schedules.entity.User;
import com.sparta.schedules.entity.UserRoleEnum;
import com.sparta.schedules.repository.ScheduleRepository;
import com.sparta.schedules.service.ScheduleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@ExtendWith(MockitoExtension.class)
public class ScheduleServiceTest {

    @Mock
    ScheduleRepository MockScheduleRepository;


    @Test
    void update(){
        //given
        User user = new User("user","user","user@gmail.com", UserRoleEnum.USER);
        user.setId(1L);
        ScheduleRequestDto requestDto = new ScheduleRequestDto();
        requestDto.setContents("테스트용 스케줄 변경 합니다");

        Schedule schedule = new Schedule(requestDto,user);
        ScheduleService scheduleService = new ScheduleService(MockScheduleRepository);

        //when
        given(MockScheduleRepository.findById(1L)).willReturn(Optional.of(schedule));
        Long l = scheduleService.updateSchedule(1L, requestDto, user);

        //then
        assertEquals(1L,l);
    }
    @Test
    void create(){
        // given
        ScheduleRequestDto requestDto = new ScheduleRequestDto();
        requestDto.setContents("테스트용 스케줄");
        LocalDate date = LocalDate.now();
        requestDto.setDate(date);

        User user = new User("user","user","harrisbang98@gmail.com", UserRoleEnum.USER);
        user.setId(1L);
        List<Schedule> schedules = new ArrayList<>();
        user.setSchedules(schedules);

        Schedule schedule = new Schedule();
        schedule.setUser(user);
        schedule.setId(1L);
        schedule.setContents("init");
        schedule.setDate(LocalDate.now());

        ScheduleService scheduleService = new ScheduleService(MockScheduleRepository);
        //when

        when(MockScheduleRepository.save(any())).thenReturn(schedule);
        ScheduleResponseDto scheduleServicesSchedule= scheduleService.createSchedule(requestDto,user);
        //then
        assertEquals(scheduleServicesSchedule.getContents(),schedule.getContents());
    }
    @Test
    void delete(){
    //given
        User user = new User("user","user","harrisbang98@gmail.com", UserRoleEnum.USER);
        user.setId(1L);
        Schedule schedule = new Schedule();
        schedule.setId(1L);
        schedule.setUser(user);
        ScheduleService scheduleService = new ScheduleService(MockScheduleRepository);
        //when
        given(MockScheduleRepository.findById(1L)).willReturn(Optional.of(schedule));
        Long l = scheduleService.deleteSchedule(1L, user);
        //then
        assertEquals(1L,l);
    }
}
