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
    public ResponseEntity<ResponseDto<?>> CreateDailySchedule(
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
    public ResponseEntity<List<ScheduleResponseDto>> DisplayAllDaily(
        HttpServletRequest request) {
        return ResponseEntity
            .status(HttpStatus.OK)
                .body(service.getSchedule(request.getHeader(AUTHORIZATION_HEADER)));

    }
    // 수정 확인
    @PutMapping("/schedule/{id}")
    public ResponseEntity<?> updateSchedule(@PathVariable(name = "id") Long id,
        @RequestBody ScheduleRequestDto requestDto,
        HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.updateSchedule(id,requestDto,request.getHeader(AUTHORIZATION_HEADER)));
    }
    // deleting the item.
    @DeleteMapping("/schedule/{id}")
    public ResponseEntity<?> deleteDailySchedule(
        @PathVariable(name = "id") Long id,
        HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.deleteSchedule(id,request.getHeader(AUTHORIZATION_HEADER)));
    }
    // searching
    @GetMapping("/schedule/search/{id}")
    public ResponseEntity<?> searchDailySchedule(
        @PathVariable(name = "id") Long id,
        HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.searchMemo(id,request.getHeader(AUTHORIZATION_HEADER)));
    }
    // searching by date
    @GetMapping("/schedule/search/date/{date}")
    public List<ScheduleResponseDto> searchByDate(
        @PathVariable(name="date") LocalDate date,
        HttpServletRequest request){
       return service.searchMemoDate(date,request.getHeader(AUTHORIZATION_HEADER));
    }
}