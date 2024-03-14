package com.sparta.schedules.controller;

import com.sparta.schedules.dto.ResponseDto;
import com.sparta.schedules.dto.ScheduleRequestDto;
import com.sparta.schedules.dto.ScheduleResponseDto;
import com.sparta.schedules.security.UserDetailsImpl;
import com.sparta.schedules.service.ScheduleService;
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

    @Autowired
    public ScheduleController(ScheduleService service) {
        this.service = service;
    }

    @PostMapping("/schedule")
    public ResponseEntity<ResponseDto<?>> CreateDailySchedule(
        @RequestBody ScheduleRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
       return ResponseEntity.status(HttpStatus.CREATED)
               .body(ResponseDto.
                   <ScheduleResponseDto>builder()
                   .data(service.createSchedule(requestDto,userDetails.getUser()))
                   .statusCode(201)
                   .build());
    }
    // 가저오기
    @GetMapping("/schedule")
    public ResponseEntity<List<ScheduleResponseDto>> DisplayAllDaily(
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity
            .status(HttpStatus.OK)
                .body(service.getSchedule(userDetails.getUser()));

    }
    // 수정 확인
    @PutMapping("/schedule/{id}")
    public ResponseEntity<?> updateSchedule(@PathVariable(name = "id") Long id,
        @RequestBody ScheduleRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.updateSchedule(id,requestDto,userDetails.getUser()));
    }
    // deleting the item.
    @DeleteMapping("/schedule/{id}")
    public ResponseEntity<?> deleteDailySchedule(@PathVariable(name = "id") Long id,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.deleteSchedule(id,userDetails.getUser()));
    }
    // searching
    @GetMapping("/schedule/search/{id}")
    public ResponseEntity<?> searchDailySchedule(@PathVariable(name = "id") Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.searchMemo(id,userDetails.getUser()));
    }
    // searching by date
    @GetMapping("/schedule/search/date/{date}")
    public List<ScheduleResponseDto> searchByDate(@PathVariable(name="date") LocalDate date,@AuthenticationPrincipal UserDetailsImpl userDetails){
       return service.searchMemoDate(date,userDetails.getUser());
    }
}