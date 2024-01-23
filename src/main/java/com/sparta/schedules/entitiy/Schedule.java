package com.sparta.schedules.entitiy;

import com.sparta.schedules.DTO.ScheduleRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "schedule")
@NoArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "password", nullable = true)
    private String password;
    @Column(name = "contents", nullable = false)
    private String contents;
    @Column(name = "date", nullable = false)
    private LocalDate date;

    public Schedule(ScheduleRequestDto requestDto) {
        this.password = requestDto.getPassword();
        this.contents = requestDto.getContents();
        this.date = requestDto.getDate();
    }

    public void update(ScheduleRequestDto requestDto) {
        this.password = requestDto.getPassword();
        this.contents = requestDto.getContents();
        this.date = requestDto.getDate();
    }
}