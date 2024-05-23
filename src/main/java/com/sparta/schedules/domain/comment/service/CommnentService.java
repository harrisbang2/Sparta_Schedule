package com.sparta.schedules.domain.comment.service;

import com.sparta.schedules.domain.comment.dto.CommentRequestDto;
import com.sparta.schedules.domain.comment.dto.CommentResponseDto;
import com.sparta.schedules.domain.comment.entity.Comment;
import com.sparta.schedules.domain.comment.repository.CommentRepository;
import com.sparta.schedules.domain.schedule.entity.Schedule;
import com.sparta.schedules.domain.schedule.repository.ScheduleRepository;
import com.sparta.schedules.domain.user.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

public interface CommnentService {

        // 조회
        public List<CommentResponseDto> getComments(Long id);

        // 생성
        public CommentResponseDto createComment(CommentRequestDto requestDto, User user);

        // 수정
        public Long updateComment(Long id, CommentRequestDto requestDto, User user);

        //삭제
        public Long deleteComment(Long id, User user);
}
