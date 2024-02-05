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
    public List<ScheduleResponseDto> DisplayAllDaily() {
        return service.getSchedule();
    }

    // 수정 확인
    @PutMapping("/schedule/{id}")
    public Long updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return service.updateSchedule(id,requestDto,userDetails.getUser());
    }
    // deleting the item.
    @DeleteMapping("/schedule/{id}")
    public Long deleteDailySchedule(@PathVariable Long id,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return service.deleteSchedule(id,userDetails.getUser());
    }

    // searching
    @GetMapping("/schedule/search/{id}")
    public Schedule searchDailySchedule(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return service.SearchMemo(id,userDetails.getUser());
    }
    @GetMapping("/schedule/search/date/{date}")
    public List searchByDate(@PathVariable LocalDate date,@AuthenticationPrincipal UserDetailsImpl userDetails){
        return service.getSchedule();
//        return service.SearchMemoDate(date,userDetails.getUser());
    }
}