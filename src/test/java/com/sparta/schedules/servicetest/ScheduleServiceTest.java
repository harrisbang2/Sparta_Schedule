package com.sparta.schedules.servicetest;

import com.sparta.schedules.dto.ScheduleRequestDto;
import com.sparta.schedules.dto.ScheduleResponseDto;
import com.sparta.schedules.entity.Schedule;
import com.sparta.schedules.entity.User;
import com.sparta.schedules.entity.UserRoleEnum;
import com.sparta.schedules.jwt.JwtUtil;
import com.sparta.schedules.repository.ScheduleRepository;
import com.sparta.schedules.repository.UserRepository;
import com.sparta.schedules.service.ScheduleServices;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Null;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.reactive.function.server.MockServerRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.lenient;
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

        ScheduleServices scheduleServices = new ScheduleServices(MockScheduleRepository);
        //when

        when(MockScheduleRepository.save(any())).thenReturn(schedule);
        ScheduleResponseDto scheduleServicesSchedule= scheduleServices.createSchedule(requestDto,user);
        //then
        assertEquals(scheduleServicesSchedule.getContents(),schedule.getContents());
    }
    @Test
    void update(){
        //given
        User user = new User("user","user","harrisbang98@gmail.com", UserRoleEnum.USER);
        user.setId(1L);
        ScheduleRequestDto requestDto = new ScheduleRequestDto();
        requestDto.setContents("테스트용 스케줄 변경 합니다");
        requestDto.setDate(LocalDate.now());
        Schedule schedule = new Schedule(requestDto,user);
        ScheduleServices scheduleServices = new ScheduleServices(MockScheduleRepository);
        //when
        given(MockScheduleRepository.findById(1L)).willReturn(Optional.of(schedule));
        Long l = scheduleServices.updateSchedule(1L, requestDto, user);
        //then
        assertEquals(1L,l);
    }
    @Test
    void delete(){
    //given
        User user = new User("user","user","harrisbang98@gmail.com", UserRoleEnum.USER);
        user.setId(1L);
        Schedule schedule = new Schedule();
        schedule.setId(1L);
        schedule.setUser(user);
        ScheduleServices scheduleServices = new ScheduleServices(MockScheduleRepository);
        //when
        given(MockScheduleRepository.findById(1L)).willReturn(Optional.of(schedule));
        Long l = scheduleServices.deleteSchedule(1L, user);
        //then
        assertEquals(1L,l);
    }
}