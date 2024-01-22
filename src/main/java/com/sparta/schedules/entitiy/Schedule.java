package com.sparta.schedules.entitiy;

import com.sparta.schedules.DTO.ScheduleRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public Schedule(ScheduleRequestDto requestDto) {
        this.password = requestDto.getPassword();
        this.contents = requestDto.getContents();
    }

    public void update(ScheduleRequestDto requestDto) {
        this.password = requestDto.getPassword();
        this.contents = requestDto.getContents();
    }
}