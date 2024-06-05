package com.sparta.schedules.domain.schedule.controller;

import com.sparta.schedules.domain.schedule.dto.ScheduleRequestDto;
import com.sparta.schedules.domain.schedule.dto.ScheduleResponseDto;
import com.sparta.schedules.domain.schedule.service.ScheduleService;
import com.sparta.schedules.domain.schedule.service.ScheduleServiceImpl;
import com.sparta.schedules.global.security.UserDetailsImpl;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ScheduleController {
    private ScheduleServiceImpl service;

    @Autowired
    public ScheduleController(ScheduleServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/schedule")
    public ResponseEntity<?> CreateDailySchedule(@RequestBody ScheduleRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(service.createSchedule(requestDto,userDetails.getUser()));
    }
    // 가저오기
    @GetMapping("/schedule")
    public List<ScheduleResponseDto> DisplayAllDaily(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return service.getSchedule(userDetails.getUser());
    }
    // 수정 확인
    @PutMapping("/schedule/{id}")
    public ResponseEntity<?> updateSchedule(@PathVariable(name = "id") Long id, @RequestBody ScheduleRequestDto requestDto,@AuthenticationPrincipal UserDetailsImpl userDetails) {
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
