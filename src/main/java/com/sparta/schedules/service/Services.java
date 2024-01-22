package com.sparta.schedules.service;

import com.sparta.schedules.DTO.ScheduleRequestDto;
import com.sparta.schedules.DTO.ScheduleResponseDto;
import com.sparta.schedules.entitiy.Schedule;
import com.sparta.schedules.repository.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Service
public class Services{

    private final Repository memoRepository;

    public Services(Repository memoRepository) {
        this.memoRepository = memoRepository;
    }

    public ScheduleResponseDto createMemo(ScheduleRequestDto requestDto) {
        // RequestDto -> Entity
        Schedule memo = new Schedule(requestDto);

        // DB 저장
        Schedule saveMemo = memoRepository.save(memo);

        // Entity -> ResponseDto
        ScheduleResponseDto memoResponseDto = new ScheduleResponseDto(saveMemo);

        return memoResponseDto;
    }

    public List<ScheduleResponseDto> getMemos() {
        // DB 조회
        return memoRepository.findAll().stream().map(ScheduleResponseDto::new).toList();
    }

    @Transactional
    public Long updateMemo(Long id, ScheduleRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        Schedule memo = findMemo(id);

        // memo 내용 수정
        memo.update(requestDto);

        return id;
    }

    public Long deleteMemo(Long id) {
        // 해당 메모가 DB에 존재하는지 확인
        Schedule memo = findMemo(id);

        // memo 삭제
        memoRepository.delete(memo);

        return id;
    }

    private Schedule findMemo(Long id) {
        return memoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
        );
    }
}