package com.sparta.schedules.controller;

import com.sparta.schedules.dto.ResponseDto;
import com.sparta.schedules.dto.ScheduleRequestDto;
import com.sparta.schedules.dto.ScheduleResponseDto;
import com.sparta.schedules.security.UserDetailsImpl;
import com.sparta.schedules.service.ScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ScheduleController {
    private ScheduleService service;
    public static final String AUTHORIZATION_HEADER = "Authorization";
    @Autowired
    public ScheduleController(ScheduleService service) {
        this.service = service;
    }

    @PostMapping("/schedule")
    public ResponseEntity<ResponseDto<ScheduleResponseDto>> CreateDailySchedule(
        @RequestBody ScheduleRequestDto requestDto,
        HttpServletRequest request) {
       return ResponseEntity.status(HttpStatus.CREATED)
               .body(ResponseDto.
                   <ScheduleResponseDto>builder()
                   .data(service.createSchedule(requestDto,request.getHeader(AUTHORIZATION_HEADER)))
                   .statusCode(201)
                   .build());
    }
    // 가저오기
    @GetMapping("/schedule")
    public ResponseEntity<ResponseDto<List<ScheduleResponseDto>>> DisplayAllDaily(
        HttpServletRequest request) {
        return ResponseEntity
            .status(HttpStatus.OK)
                .body(ResponseDto.
                    <List<ScheduleResponseDto>>builder()
                    .data(service.getSchedule(request.getHeader(AUTHORIZATION_HEADER)))
                    .statusCode(200)
                    .build())
            ;

    }
    // 수정 확인
    @PutMapping("/schedule/{id}")
    public ResponseEntity<ResponseDto<Long>> updateSchedule(@PathVariable(name = "id") Long id,
        @RequestBody ScheduleRequestDto requestDto,
        HttpServletRequest request) {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(ResponseDto.
                <Long>builder()
                .data(service.updateSchedule(id,requestDto,request.getHeader(AUTHORIZATION_HEADER)))
                .statusCode(200)
                .build())
            ;
    }
    // deleting the item.
    @DeleteMapping("/schedule/{id}")
    public ResponseEntity<ResponseDto<Long>> deleteDailySchedule(
        @PathVariable(name = "id") Long id,
        HttpServletRequest request) {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(ResponseDto.
                <Long>builder()
                .data(service.deleteSchedule(id,request.getHeader(AUTHORIZATION_HEADER)))
                .statusCode(200)
                .build())
            ;

    }
    // searching
    @GetMapping("/schedule/search/{id}")
    public ResponseEntity<ResponseDto<ScheduleResponseDto>> searchDailySchedule(
        @PathVariable(name = "id") Long id,
        HttpServletRequest request){
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(ResponseDto.
                <ScheduleResponseDto>builder()
                .data(service.searchMemo(id,request.getHeader(AUTHORIZATION_HEADER)))
                .statusCode(200)
                .build())
            ;
    }
    // searching by date
    @GetMapping("/schedule/search/date/{date}")
    public ResponseEntity<ResponseDto<List<ScheduleResponseDto>>> searchByDate(
        @PathVariable(name="date") LocalDate date,
        HttpServletRequest request){
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(ResponseDto.
                <List<ScheduleResponseDto>>builder()
                .data(service.searchMemoDate(date,request.getHeader(AUTHORIZATION_HEADER)))
                .statusCode(200)
                .build())
            ;
    }
}