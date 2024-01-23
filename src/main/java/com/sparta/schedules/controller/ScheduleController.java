package com.sparta.schedules.controller;

import com.sparta.schedules.DTO.ScheduleRequestDto;
import com.sparta.schedules.DTO.ScheduleResponseDto;
import com.sparta.schedules.entitiy.Schedule;
import com.sparta.schedules.service.Services;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleController {
    Services service ;
    public ScheduleController(Services service) {
        this.service = service;
    }

    @PostMapping("/schedule")
    public ScheduleResponseDto CreateDailySchedule(@RequestBody ScheduleRequestDto requestDto) {
       return service.createSchedule(requestDto);
    }

    @GetMapping("/schedule")
    public List<ScheduleResponseDto> DisplayAllDaily() {
        return service.getSchedule();
    }

    @PutMapping("/schedule/{id}")
    public Long updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        return service.updateSchedule(id,requestDto);
    }

    @DeleteMapping("/schedule/{id}")
    public Long DeleteDailySchedule(@PathVariable Long id) {
        return service.deleteSchedule(id);
    }
    // getting password
    @GetMapping("/schedule/password/{id}")
    public String VertifyPass(@PathVariable Long id){
        return service.SearchMemo(id).getPassword();
    }
    // searching
    @GetMapping("/schedule/search/{id}")
    public Schedule SearchDailySchedule(@PathVariable Long id){
        return service.SearchMemo(id);
    }
    @GetMapping("/schedule/search/date/{id}")
    public List SearchByDate(@PathVariable LocalDate id){
        return service.SearchMemoDate(id);
    }
}