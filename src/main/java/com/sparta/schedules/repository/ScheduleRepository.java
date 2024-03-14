package com.sparta.schedules.repository;

import com.sparta.schedules.entity.Schedule;
import com.sparta.schedules.entity.User;
import com.sparta.schedules.repository.projectionInterface.ScheduleCotentsDateOnly;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    List<ScheduleCotentsDateOnly> findAllByDateAndUser(LocalDate date, User user);

    @Query("select t from Schedule t join fetch t.user")
    Schedule findByIdAndUser(Long id, User user);

    List<ScheduleCotentsDateOnly> findByUser(User user);
}
