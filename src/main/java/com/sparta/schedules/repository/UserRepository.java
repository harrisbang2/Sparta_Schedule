package com.sparta.schedules.repository;

import com.sparta.schedules.entity.User;
import com.sparta.schedules.repository.projectionInterface.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<UserProfile> findByEmail(String email);

    Optional<User> findByUsername(String username);
}
