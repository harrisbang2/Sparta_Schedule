package com.sparta.schedules.service;

import com.sparta.schedules.dto.ScheduleRequestDto;
import com.sparta.schedules.dto.ScheduleResponseDto;
import com.sparta.schedules.entity.Schedule;
import com.sparta.schedules.entity.User;
import com.sparta.schedules.repository.ScheduleRepository;

import com.sparta.schedules.repository.projectionInterface.ScheduleCotentsDateOnly;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScheduleService {
    private final ScheduleRepository ScRepository;
    public ScheduleService(ScheduleRepository ScRepository) {
        this.ScRepository = ScRepository;

    }
    //// create
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto, String token) {
        User user = makeMockUser(token);
        // RequestDto -> Entity
        Schedule sc = new Schedule(requestDto,user);

        // DB 저장
        Schedule saves = ScRepository.save(sc);

        // Entity -> ResponseDto
        return new ScheduleResponseDto(saves);
    }

    //// get

    public List<ScheduleResponseDto> getSchedule(String token) {
        User user = makeMockUser(token);
        // DB 조회
        List<ScheduleCotentsDateOnly> sclist = ScRepository.findByUser(user);
        List<ScheduleResponseDto> scr = new ArrayList<>();
//
        for(ScheduleCotentsDateOnly sc : sclist){
            scr.add(new ScheduleResponseDto(sc));
        }
        return scr;
    }
    ////update
    @Transactional
    public Long updateSchedule(Long id, ScheduleRequestDto requestDto, String token) {
        User user = makeMockUser(token);
        //  DB에 존재하는지 확인
        Schedule sc = ScRepository.findById(id).orElseThrow(()-> new NoSuchElementException("해당 일정 찾을수 없습니다."));
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
    @Transactional
    public Long deleteSchedule(Long id, String token) {
        User user = makeMockUser(token);
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
    public ScheduleResponseDto searchMemo(Long id, String token) {
        User user = makeMockUser(token);
        Schedule sc = ScRepository.findByIdAndUser(id,user);
      return new ScheduleResponseDto(sc);
    }
    public List<ScheduleResponseDto> searchMemoDate(LocalDate id, String token) {
        User user = makeMockUser(token);
        List<ScheduleCotentsDateOnly> sclist = ScRepository.findAllByDateAndUser(id,user);
        List<ScheduleResponseDto> scr = new ArrayList<>();
       for(ScheduleCotentsDateOnly sc : sclist){
           scr.add(new ScheduleResponseDto(sc));
       }
        return scr;
    }



    /*
    *
    *
    *
     */
    private  User makeMockUser(String token){
        token = token.substring(9);

        JwtParser jwtParser = Jwts.parserBuilder()
            .setSigningKey("7Iqk7YyM66W07YOA7L2U65Sp7YG065+9U3ByaW5n6rCV7J2Y7Yqc7YSw7LWc7JuQ67mI7J6F64uI64ukLg==")
            .build();
        Claims claims = jwtParser
            .parseClaimsJws(token)
            .getBody();
        return new User((long)(int) claims.get("userId"));
    }
}