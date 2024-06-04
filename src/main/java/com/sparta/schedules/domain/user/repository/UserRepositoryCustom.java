package com.sparta.schedules.domain.user.repository;

import com.sparta.schedules.domain.user.entity.User;
import java.util.Optional;

public interface UserRepositoryCustom {
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameAndPassword(String username,String password);
}
