package com.sparta.schedules.repository;

import com.sparta.schedules.DTO.ScheduleRequestDto;
import com.sparta.schedules.DTO.ScheduleResponseDto;
import com.sparta.schedules.entitiy.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
@Component
public class Repository {
    private final JdbcTemplate jdbcTemplate;
    public Repository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /// save
    public ScheduleResponseDto save(Schedule memo) {
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

    public List<ScheduleResponseDto> getAll() {
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

    public Long update(long id, ScheduleRequestDto requestDto) {
        String sql = "UPDATE schedule SET password = ?, contents = ? WHERE id = ?";
        jdbcTemplate.update(sql, requestDto.getPassword(), requestDto.getContents(), id);

        return id;
    }

    public void delete(Long id) {
        String sql = "DELETE FROM schedule WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
