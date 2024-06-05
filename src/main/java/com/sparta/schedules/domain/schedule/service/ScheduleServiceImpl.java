package com.sparta.schedules.domain.schedule.service;

import com.sparta.schedules.domain.schedule.entity.Schedule;
import com.sparta.schedules.domain.schedule.repository.ScheduleRepository;
import com.sparta.schedules.domain.user.entity.User;
import com.sparta.schedules.domain.schedule.dto.ScheduleRequestDto;
import com.sparta.schedules.domain.schedule.dto.ScheduleResponseDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{

    private final ScheduleRepository ScRepository;

    @Override
    //// create
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto, User user) {
        // RequestDto -> Entity
        Schedule sc = new Schedule(requestDto,user);

        // DB 저장
        Schedule saves = ScRepository.save(sc);

        // Entity -> ResponseDto
        return new ScheduleResponseDto(saves);
    }

    //// get
    @Override
    public List<ScheduleResponseDto> getSchedule(User user) {
        // DB 조회
        return ScRepository.findByUser(user);
    }

    ////update
    @Override
    @Transactional
    public Long updateSchedule(Long id, ScheduleRequestDto requestDto, User user) {

        Schedule sc = findMemo(id);

        if(sc.getUser().getId().equals(user.getId())){
            sc.update(requestDto);
        }
        else {
            throw new IllegalStateException("유저가 다릅니다!!!!");
        }
        return id;
    }

    ////delete
    @Override
    public Long deleteSchedule(Long id, User user) {
        // 해당 DB에 존재하는지 확인
        Schedule sc = findMemo(id);
        // 유저 확인.
        if(sc.getUser().getId().equals(user.getId())){
            // memo 삭제
            ScRepository.delete(sc);
        }
        else {
            throw new IllegalStateException("유저가 다릅니다!!!!");
        }
        return id;
    }

    // 검색 //
    // Find by ID + user
    @Override
    public ScheduleResponseDto searchMemo(Long id, User user) {
        return ScRepository.findByIdAndUser(id,user);
    }
    @Override
    public List<ScheduleResponseDto> searchMemoDate(LocalDate id, User user) {
        return ScRepository.findAllByDateAndUser(id,user);
    }

    // Find by ID
    private Schedule findMemo(Long id) {
        return ScRepository.findById(id).orElseThrow();
    }
}
