package com.sparta.schedules.repository;

import com.sparta.schedules.entity.Schedule;
import com.sparta.schedules.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    List<Schedule> findAllByDateAndUser(LocalDate date, User user);

    Schedule findByIdAndUser(Long id, User user);

    List<Schedule> findByUser(User user);
}
