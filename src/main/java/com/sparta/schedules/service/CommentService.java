package com.sparta.schedules.service;

import com.sparta.schedules.dto.CommentRequestDto;
import com.sparta.schedules.dto.CommentResponseDto;
import com.sparta.schedules.dto.ScheduleResponseDto;
import com.sparta.schedules.entity.Comment;
import com.sparta.schedules.entity.Schedule;
import com.sparta.schedules.entity.User;
import com.sparta.schedules.repository.CommentRepository;
import com.sparta.schedules.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    @Autowired
    private final CommentRepository commentRepository;
    @Autowired
    private final ScheduleRepository ScRepository;


    public List<CommentResponseDto> getComments(Long id) {
        Schedule sc = ScRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("그런 스케줄 없음"));
        return commentRepository.findAllBySchedule(sc);
    }

    public CommentResponseDto createComment(CommentRequestDto requestDto, User user) {
        Schedule sc = ScRepository.findById(requestDto.getSchedule_id()).orElseThrow(() -> new IllegalArgumentException("그런 스케줄 없음"));
        Comment comment = new Comment(requestDto,sc,user);
        Comment savecomment = commentRepository.save(comment);
        CommentResponseDto res = new CommentResponseDto();
        res.setComment(savecomment.getComment());
        return res;
    }
}
