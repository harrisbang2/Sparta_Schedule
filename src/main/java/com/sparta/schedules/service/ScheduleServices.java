package com.sparta.schedules.service;

import com.sparta.schedules.Dto.ScheduleRequestDto;
import com.sparta.schedules.Dto.ScheduleResponseDto;
import com.sparta.schedules.entity.Schedule;
import com.sparta.schedules.entity.User;
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

    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto, User user) {
        // RequestDto -> Entity
        Schedule sc = new Schedule(requestDto,user);

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
    public Long updateSchedule(Long id, ScheduleRequestDto requestDto, User user) {
        // 해당 메모가 DB에 존재하는지 확인
        Schedule sc = findMemo(id, user);

        // memo 내용 수정
        sc.update(requestDto);

        return id;
    }

    public Long deleteSchedule(Long id, User user) {
        // 해당 메모가 DB에 존재하는지 확인
        Schedule sc = findMemo(id,user);

        // memo 삭제
        ScRepository.delete(sc);

        return id;
    }

    private Schedule findMemo(Long id, User user) {
        return ScRepository.findByIdAndUser(id,user);
    }

    // 검색
    public Schedule SearchMemo(Long id, User user) {
        System.out.println("아이디 :"+id+"user : " +user);
        System.out.println("아이디 :"+id+"user : " +user);
        System.out.println("아이디 :"+id+"user : " +user);
        System.out.println("아이디 :"+id+"user : " +user);
        System.out.println("아이디 :"+id+"user : " +user);
        System.out.println("아이디 :"+id+"user : " +user);

        return ScRepository.findByIdAndUser(id,user);
    }
    public List SearchMemoDate(LocalDate id, User user) {
        return ScRepository.findAllByDateAndUser(id,user);
    }
}