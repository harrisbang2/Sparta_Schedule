package com.sparta.schedules.domain.user.repository;

import com.sparta.schedules.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);
}
