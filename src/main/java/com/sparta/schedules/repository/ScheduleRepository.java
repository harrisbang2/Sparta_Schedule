package com.sparta.schedules.repository;

import com.sparta.schedules.dto.ScheduleResponseDto;
import com.sparta.schedules.entity.Schedule;
import com.sparta.schedules.entity.User;
import com.sparta.schedules.repository.projectionInterfaces.ScheduleCotentsDateOnly;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    List<Schedule> findAllByDateAndUser(LocalDate date, User user);

    Schedule findByIdAndUser(Long id, User user);


    List<ScheduleCotentsDateOnly> findByUser(User user);
}
