package com.sparta.schedules.entity;


import jakarta.persistence.EntityListeners;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class TimeStamp {
  private LocalDateTime createdAt;

  private LocalDateTime modifiedAt;

  public void updateCreatedAt() {
    this.createdAt = LocalDateTime.now();
  }

  public void updateModifiedAt() {
    this.modifiedAt = LocalDateTime.now();
  }
}
