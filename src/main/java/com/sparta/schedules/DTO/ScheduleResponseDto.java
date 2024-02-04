package com.sparta.schedules.DTO;

import com.sparta.schedules.entity.Schedule;
import lombok.Getter;

import java.time.LocalDate;

@Getter
    public class ScheduleResponseDto {
        private Long id;
        private String contents;
        private LocalDate date;

        public ScheduleResponseDto(Schedule sc) {
            this.id = sc.getId();
            this.contents = sc.getContents();
            this.date = sc.getDate();
        }

        public ScheduleResponseDto(Long id, String contents,LocalDate date) {
        this.id = id;
        this.contents = contents;
        this.date = date;
        }
    }

