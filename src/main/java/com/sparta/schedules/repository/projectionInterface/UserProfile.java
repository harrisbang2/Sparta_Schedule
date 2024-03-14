package com.sparta.schedules.repository.projectionInterface;

import com.sparta.schedules.entity.UserRoleEnum;

public interface UserProfile {
  String getUsername();

  String getPassword();
  String getEmail();
  UserRoleEnum getRole();
}
