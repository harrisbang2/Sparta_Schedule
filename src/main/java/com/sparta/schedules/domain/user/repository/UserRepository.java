package com.sparta.schedules.domain.user.repository;

import com.sparta.schedules.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>,UserRepositoryCustom{

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
