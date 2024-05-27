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

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommnentService{
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    // 조회
    @Override
    public List<CommentResponseDto> getComments(Long id) {
        return commentRepository.findAllBySchedule(id);
    }
    @Override
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

    @Override
    ///// 수정
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

    @Override
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

    private Comment findSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("그런 스케줄 없음"));
        return commentRepository.findBySchedule(schedule);
    }
}

