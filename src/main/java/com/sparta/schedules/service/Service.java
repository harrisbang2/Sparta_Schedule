package com.sparta.schedules.service;

import com.sparta.schedules.DTO.ScheduleRequestDto;
import com.sparta.schedules.DTO.ScheduleResponseDto;
import com.sparta.schedules.entitiy.Schedule;
import com.sparta.schedules.repository.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Service {
    private final JdbcTemplate jdbcTemplate;
    Repository repo;
    public Service(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        repo = new Repository(jdbcTemplate);
    }
    //////////////////////////////// create
    public ScheduleResponseDto createMemo(ScheduleRequestDto requestDto) {
        // RequestDto -> Entity
        Schedule memo = new Schedule(requestDto);
        return repo.save(memo);


    }
    //////////////////////////////// get
    public List<ScheduleResponseDto> getSchedules() {
        return repo.getAll();
        // DB 조회
    }
    //////////////////////////////// update
    public Long updateSchedule(long id, ScheduleRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        Schedule memo = findById(id);
        if(memo != null) {
            // memo 내용 수정
           return  repo.update(id,requestDto);
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }



    //////////////////////////////// find
    private Schedule findById(Long id) {
        // DB 조회
        String sql = "SELECT * FROM schedule WHERE id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if(resultSet.next()) {
                Schedule memo = new Schedule();
                memo.setPassword(resultSet.getString("password"));
                memo.setContents(resultSet.getString("contents"));
                return memo;
            } else {
                return null;
            }
        }, id);
    }

    public Long delete(Long id) {
        // 해당 메모가 DB에 존재하는지 확인
        Schedule memo = findById(id);
        if(memo != null) {
            // memo 삭제
            repo = new Repository(jdbcTemplate);
            repo.delete(id);
            return id;
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }
}
