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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

import static org.awaitility.Awaitility.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ScheduleServiceTest {

    @Autowired
    PasswordEncoder passwordEncoder;
    JwtUtil jwtUtil;
    @Mock
    ScheduleRepository ScheduleRepository;
    @Mock
    UserRepository userRepository;

    @Mock
    HttpServletResponse res;

    @Test
    void create(){
        // given
        ScheduleRequestDto requestDto = new ScheduleRequestDto();

        requestDto.setContents("테스트용 스케줄");
        LocalDate date = LocalDate.now();
        requestDto.setDate(date);

        User user = new User("user","user","harrisbang98@gmail.com", UserRoleEnum.USER);
        user.setId(1L);

        //when
        boolean hasError = false;
        try{
            Schedule sc = new Schedule(requestDto,user);
            Schedule saves = ScheduleRepository.save(sc);
        }catch (Exception e){
            hasError = true;
        }
        //then
        assertFalse(hasError);
    }
    @Test
    void get(){
        
    }
    @Test
    void update(){

    }
    @Test
    void delete(){

    }
}
