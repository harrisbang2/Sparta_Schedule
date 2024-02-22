package com.sparta.schedules.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
    public class ScheduleRequestDto {
    private LocalDate date;
    private String contents;
    }




