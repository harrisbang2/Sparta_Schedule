package com.sparta.schedules.controller;

import com.sparta.schedules.Dto.ScheduleRequestDto;
import com.sparta.schedules.Dto.ScheduleResponseDto;
import com.sparta.schedules.entity.Schedule;
import com.sparta.schedules.security.UserDetailsImpl;
import com.sparta.schedules.service.ScheduleServices;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleController {
    ScheduleServices service ;
    public ScheduleController(ScheduleServices service) {
        this.service = service;
    }

    @PostMapping("/schedule")
    public ScheduleResponseDto CreateDailySchedule(@RequestBody ScheduleRequestDto requestDto,@AuthenticationPrincipal UserDetailsImpl userDetails) {
       return service.createSchedule(requestDto,userDetails.getUser());
    }

    // 가저오기
    @GetMapping("/schedule")
    public List<ScheduleResponseDto> DisplayAllDaily(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return service.getSchedule(userDetails.getUser());
    }

    // 수정 확인
    @PutMapping("/schedule/{id}")
    public Long updateSchedule(@PathVariable(name = "id") Long id, @RequestBody ScheduleRequestDto requestDto,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return service.updateSchedule(id,requestDto,userDetails.getUser());
    }
    // deleting the item.
    @DeleteMapping("/schedule/{id}")
    public Long deleteDailySchedule(@PathVariable(name = "id") Long id,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return service.deleteSchedule(id,userDetails.getUser());
    }

    // searching
    @GetMapping("/schedule/search/{id}")
    public ScheduleResponseDto searchDailySchedule(@PathVariable(name = "id") Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return service.searchMemo(id,userDetails.getUser());
    }
    // searching by date
    @GetMapping("/schedule/search/date/{date}")
    public List<ScheduleResponseDto> searchByDate(@PathVariable(name="date") LocalDate date,@AuthenticationPrincipal UserDetailsImpl userDetails){
       return service.searchMemoDate(date,userDetails.getUser());
    }
}