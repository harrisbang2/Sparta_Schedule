package com.sparta.schedules.servicetest;

import com.sparta.schedules.dto.CommentRequestDto;
import com.sparta.schedules.entity.Comment;
import com.sparta.schedules.entity.Schedule;
import com.sparta.schedules.entity.User;
import com.sparta.schedules.entity.UserRoleEnum;
import com.sparta.schedules.repository.CommentRepository;
import com.sparta.schedules.repository.CommentRepositoryCustom;
import com.sparta.schedules.repository.ScheduleRepository;
import com.sparta.schedules.service.CommentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {
    @Mock
    CommentRepository MockCommentRepository;
    @Mock
    ScheduleRepository MockscheduleRepository;
    @Test
    void create(){
        // given
        CommentRequestDto requestDto = new CommentRequestDto();
        requestDto.setComment("테스트용  댓글");
        requestDto.setSchedule_id(1L);

        Schedule schedule = new Schedule();
        schedule.setId(1L);

        User user = new User();
        user.setId(1L);

        CommentService Services = new CommentService(MockCommentRepository,MockscheduleRepository);
        //when
        boolean hasError = false;
        try{
            given(MockscheduleRepository.findById(1L)).willReturn(Optional.of(schedule));
            given(MockCommentRepository.save(any())).willReturn(new Comment());
            Services.createComment(requestDto,user);
        }catch (Exception e){
            hasError = true;
        }
        //then
        assertFalse(hasError);
    }
    @Test
    void update(){
        //given
        User user = new User();
        user.setId(1L);

        CommentRequestDto requestDto = new CommentRequestDto();
        requestDto.setComment("테스트용 댓글 변경 합니다");
        requestDto.setSchedule_id(1L);

        Schedule schedule = new Schedule();
        schedule.setId(1L);
        schedule.setDate(LocalDate.now());
        schedule.setUser(user);
        schedule.setContents("init");

        Comment comment = new Comment(requestDto,schedule,user);

        CommentService commentService = new CommentService(MockCommentRepository,MockscheduleRepository);
        //when
        given(MockscheduleRepository.findById(1L)).willReturn(Optional.of(schedule));
        given(MockCommentRepository.findBySchedule(schedule)).willReturn(comment);
        //when(comment.getUser().getId().equals(any())).thenReturn(true);

        Long l = commentService.updateComment(1L, requestDto, user);
        //then
        assertEquals(1L,l);
    }
    @Test
    void delete(){
        //given
        User user = new User("user","user","harrisbang98@gmail.com", UserRoleEnum.USER);
        user.setId(1L);

        CommentRequestDto requestDto = new CommentRequestDto();
        requestDto.setComment("테스트용 댓글 변경 합니다");
        requestDto.setSchedule_id(1L);

        Schedule schedule = new Schedule();
        schedule.setId(1L);

        Comment comment = new Comment(requestDto,schedule,user);
        CommentService commentService = new CommentService(MockCommentRepository,MockscheduleRepository);
        //when
        given(MockscheduleRepository.findById(1L)).willReturn(Optional.of(schedule));
        given(MockCommentRepository.findBySchedule(schedule)).willReturn(comment);

        Long l = commentService.deleteComment(1L, user);
        //then
        assertEquals(1L,l);
    }
}
