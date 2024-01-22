package com.sparta.schedules.DTO;

import com.sparta.schedules.entitiy.Schedule;
import lombok.Getter;
    @Getter
    public class ScheduleResponseDto {
        private Long id;
        private String password;
        private String contents;

        public ScheduleResponseDto(Schedule sc) {
            this.id = sc.getId();
            this.password = sc.getPassword();
            this.contents = sc.getContents();
        }

        public ScheduleResponseDto(Long id, String password, String contents) {
        this.id = id;
        this.password = password;
        this.contents = contents;
        }
    }

