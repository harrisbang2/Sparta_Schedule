package com.sparta.schedules.service;

import com.sparta.schedules.dto.CommentRequestDto;
import com.sparta.schedules.dto.CommentResponseDto;
import com.sparta.schedules.entity.Comment;
import com.sparta.schedules.entity.Schedule;
import com.sparta.schedules.entity.User;
import com.sparta.schedules.repository.CommentRepository;
import com.sparta.schedules.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

// 조회
    public List<CommentResponseDto> getComments(Long id,User user) {
        Schedule sc = scheduleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("그런 스케줄 없음"));
        return commentRepository.findAllBySchedule(sc);
    }

    public CommentResponseDto createComment(CommentRequestDto requestDto, User user) {
        /// 스케줄 확인.
        Schedule sc = scheduleRepository.findById(requestDto.getSchedule_id()).orElseThrow(() -> new IllegalArgumentException("그런 스케줄 없음"));
        /// 받아온 정보로 댓글 만들기.
        Comment comment = new Comment(requestDto,sc,user);
        /// 저장.
        Comment savecomment = commentRepository.save(comment);
        /// ResponseDto 만들기.
        CommentResponseDto res = new CommentResponseDto();
        res.setComment(savecomment.getComment());
        return res;
    }
    ///// 수정
    @Transactional
    public Long updateComment(Long id, CommentRequestDto requestDto, User user) {
        // 해당 메모가 DB에 존재하는지 확인
        Comment comment = findSchedule(id);
        // 유저 확인.
        if(comment.getUser().getId().equals(user.getId())){
            // 내용 수정
            System.out.println(requestDto.getComment());
            comment.update(requestDto.getComment());
        }
        else {
            throw new IllegalStateException("유저가 다릅니다!!!!");
        }
        return id;
    }

    /// 삭제
    public Long deleteComment(Long id, User user) {

        // 해당 메모가 DB에 존재하는지 확인
        Comment comment = findSchedule(id);
        // 유저 확인.
        if(comment.getUser().getId().equals(user.getId())){
            // memo 내용 수정
            commentRepository.delete(comment);
        }
        else {
            throw new IllegalStateException("유저가 다릅니다!!!!");
        }
        return id;
    }
    //
    private Comment findSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("그런 스케줄 없음"));
        Comment comment = commentRepository.findBySchedule(schedule);
        return comment;
    }
}

