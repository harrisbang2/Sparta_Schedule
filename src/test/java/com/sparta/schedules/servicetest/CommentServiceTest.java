package com.sparta.schedules.servicetest;

import com.sparta.schedules.dto.CommentRequestDto;
import com.sparta.schedules.entity.Comment;
import com.sparta.schedules.entity.Schedule;
import com.sparta.schedules.entity.User;
import com.sparta.schedules.entity.UserRoleEnum;
import com.sparta.schedules.jwt.JwtUtil;
import com.sparta.schedules.repository.CommentRepository;
import com.sparta.schedules.repository.ScheduleRepository;
import com.sparta.schedules.service.CommentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    CommentRepository CommentRepository;
    @Autowired
    ScheduleRepository scheduleRepository;
    JwtUtil jwtUtil;
    @Mock
    CommentRepository MockCommentRepository;

    @Test
    void create(){
        // given
        CommentRequestDto requestDto = new CommentRequestDto();
        Schedule schedule = scheduleRepository.findById(1L).orElseThrow();
        requestDto.setComment("테스트용  댓글");

        User user = new User("user","user","harrisbang98@gmail.com", UserRoleEnum.USER);
        user.setId(1L);

        //when
        boolean hasError = false;
        try{
            Comment sc = new Comment(requestDto,schedule,user);
            Comment saves = MockCommentRepository.save(sc);
            //Comment savesreal = CommentRepository.save(sc);
        }catch (Exception e){
            hasError = true;
        }
        //then
        assertFalse(hasError);
    }
    @Test
    void update(){
        //given
        User user = new User("user","user","harrisbang98@gmail.com", UserRoleEnum.USER);
        user.setId(1L);
        CommentRequestDto requestDto = new CommentRequestDto();
        requestDto.setComment("테스트용 댓글 변경 합니다");
        CommentService Services = new CommentService(CommentRepository,scheduleRepository);
        //when
        Long l = Services.updateComment(1L, requestDto, user);
        //then
        assertEquals(1L,l);
    }
    @Test
    void delete(){
        //given
        User user = new User("user","user","harrisbang98@gmail.com", UserRoleEnum.USER);
        user.setId(1L);
        CommentService Services = new CommentService(CommentRepository,scheduleRepository);
        //when
        Long l = Services.deleteComment(1L, user);
        //then
        assertEquals(1L,l);
    }
}
