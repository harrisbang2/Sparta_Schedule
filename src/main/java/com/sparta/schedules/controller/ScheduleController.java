package com.sparta.schedules.controller;

import com.sparta.schedules.DTO.ScheduleRequestDto;
import com.sparta.schedules.DTO.ScheduleResponseDto;
import com.sparta.schedules.entitiy.Schedule;
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

    private final JdbcTemplate jdbcTemplate;

    public ScheduleController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/memos")
    public ScheduleResponseDto createMemo(@RequestBody ScheduleRequestDto requestDto) {
        // RequestDto -> Entity
        Schedule memo = new Schedule(requestDto);

        // DB 저장
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체

        String sql = "INSERT INTO schedule (password, contents) VALUES (?, ?)";
        jdbcTemplate.update( con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, memo.getPassword());
                    preparedStatement.setString(2, memo.getContents());
                    return preparedStatement;
                },
                keyHolder);

        // DB Insert 후 받아온 기본키 확인
        Long id = keyHolder.getKey().longValue();
        memo.setId(id);

        // Entity -> ResponseDto
        ScheduleResponseDto memoResponseDto = new ScheduleResponseDto(memo);

        return memoResponseDto;
    }

    @GetMapping("/memos")
    public List<ScheduleResponseDto> getMemos() {
        // DB 조회
        String sql = "SELECT * FROM schedule";

        return jdbcTemplate.query(sql, new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                // SQL 의 결과로 받아온 Memo 데이터들을 MemoResponseDto 타입으로 변환해줄 메서드
                Long id = rs.getLong("id");
                String password = rs.getString("password");
                String contents = rs.getString("contents");
                return new ScheduleResponseDto(id, password, contents);
            }
        });
    }

    @PutMapping("/memos/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        Schedule memo = findById(id);
        if(memo != null) {
            // memo 내용 수정
            String sql = "UPDATE schedule SET password = ?, contents = ? WHERE id = ?";
            jdbcTemplate.update(sql, requestDto.getPassword(), requestDto.getContents(), id);

            return id;
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }

    @DeleteMapping("/memos/{id}")
    public Long deleteMemo(@PathVariable Long id) {
        // 해당 메모가 DB에 존재하는지 확인
        Schedule memo = findById(id);
        if(memo != null) {
            // memo 삭제
            String sql = "DELETE FROM schedule WHERE id = ?";
            jdbcTemplate.update(sql, id);

            return id;
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }

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
}