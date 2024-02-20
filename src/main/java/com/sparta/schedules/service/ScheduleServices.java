package com.sparta.schedules.service;

import com.sparta.schedules.dto.ScheduleRequestDto;
import com.sparta.schedules.dto.ScheduleResponseDto;
import com.sparta.schedules.entity.Schedule;
import com.sparta.schedules.entity.User;
import com.sparta.schedules.repository.ScheduleRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScheduleServices{

    private final ScheduleRepository ScRepository;
    public ScheduleServices(ScheduleRepository ScRepository) {
        this.ScRepository = ScRepository;
    }

    //// create
    //// create
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
    //// get
    //// get
    public List<ScheduleResponseDto> getSchedule(User user) {
        // DB 조회
        List<Schedule> sclist = ScRepository.findByUser(user);
        List<ScheduleResponseDto> scr = new ArrayList<>();
        for(Schedule sc : sclist){
            scr.add(new ScheduleResponseDto(sc));
        }
        return scr;
    }
    ////update

    @Transactional
    public Long updateSchedule(Long id, ScheduleRequestDto requestDto, User user) {
        //  DB에 존재하는지 확인
        Schedule sc = findMemo(id);
        // 유저 확인.
        if(sc.getUser().getId().equals(user.getId())){
            //  내용 수정
            sc.update(requestDto);
        }
        else {
            throw new IllegalStateException("유저가 다릅니다!!!!");
        }
        return id;
    }

    ////delete

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
    //// Find by ID
    private Schedule findMemo(Long id) {
        return ScRepository.findById(id).orElseThrow();
    }

    // 검색 //// Find by ID + user
    public ScheduleResponseDto searchMemo(Long id, User user) {
        Schedule sc = ScRepository.findByIdAndUser(id,user);
        ScheduleResponseDto scr = new ScheduleResponseDto(sc);
        return scr;
    }
    public List<ScheduleResponseDto> searchMemoDate(LocalDate id, User user) {
        List<Schedule> sclist = ScRepository.findAllByDateAndUser(id,user);
        List<ScheduleResponseDto> scr = new ArrayList<>();
       for(Schedule sc : sclist){
           scr.add(new ScheduleResponseDto(sc));
       }
        return scr;
    }
}