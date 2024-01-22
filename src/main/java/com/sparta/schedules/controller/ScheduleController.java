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
import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleController {
    Services service ;
    public ScheduleController(Services service) {
        this.service = service;
    }

    @PostMapping("/memos")
    public ScheduleResponseDto createMemo(@RequestBody ScheduleRequestDto requestDto) {
       return service.createMemo(requestDto);
    }

    @GetMapping("/memos")
    public List<ScheduleResponseDto> getMemos() {
        return service.getMemos();
    }

    @PutMapping("/memos/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        return service.updateMemo(id,requestDto);
    }

    @DeleteMapping("/memos/{id}")
    public Long deleteMemo(@PathVariable Long id) {
        return service.deleteMemo(id);
    }

}