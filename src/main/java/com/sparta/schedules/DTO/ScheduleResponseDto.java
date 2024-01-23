package com.sparta.schedules.DTO;

import com.sparta.schedules.entitiy.Schedule;
import lombok.Getter;

import java.time.LocalDate;

@Getter
    public class ScheduleResponseDto {
        private Long id;
        private String password;
        private String contents;
        private LocalDate date;

        public ScheduleResponseDto(Schedule sc) {
            this.id = sc.getId();
            this.password = sc.getPassword();
            this.contents = sc.getContents();
            this.date = sc.getDate();
        }

        public ScheduleResponseDto(Long id, String password, String contents,LocalDate date) {
        this.id = id;
        this.password = password;
        this.contents = contents;
        this.date = date;
        }
    }

