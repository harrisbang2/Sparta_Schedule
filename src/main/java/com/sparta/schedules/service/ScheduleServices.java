package com.sparta.schedules.service;

import com.sparta.schedules.Dto.ScheduleRequestDto;
import com.sparta.schedules.Dto.ScheduleResponseDto;
import com.sparta.schedules.entity.Schedule;
import com.sparta.schedules.repository.ScheduleRepository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScheduleServices{

    private final ScheduleRepository ScRepository;

    public ScheduleServices(ScheduleRepository ScRepository) {
        this.ScRepository = ScRepository;
    }

    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        // RequestDto -> Entity
        Schedule sc = new Schedule(requestDto);

        // DB 저장
        Schedule savesc = ScRepository.save(sc);

        // Entity -> ResponseDto
        ScheduleResponseDto ScResponseDto = new ScheduleResponseDto(savesc);

        return ScResponseDto;
    }

    public List<ScheduleResponseDto> getSchedule() {
        // DB 조회
        return ScRepository.findAll().stream().map(ScheduleResponseDto::new).toList();
    }

    @Transactional
    public Long updateSchedule(Long id, ScheduleRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        Schedule sc = findMemo(id);

        // memo 내용 수정
        sc.update(requestDto);

        return id;
    }

    public Long deleteSchedule(Long id) {
        // 해당 메모가 DB에 존재하는지 확인
        Schedule sc = findMemo(id);

        // memo 삭제
        ScRepository.delete(sc);

        return id;
    }

    private Schedule findMemo(Long id) {
        return ScRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 데이터는 는 존재하지 않습니다.")
        );
    }

    public Schedule SearchMemo(Long id) {
        return ScRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 데이터는 존재하지 않습니다.")
        );
    }

    public List SearchMemoDate(LocalDate id) {
        return ScRepository.findByDate(id);
    }
}