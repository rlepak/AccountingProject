package com.project.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Component
public class BaseEntityListener extends AuditingEntityListener {

    @PrePersist
    private void onPrePersist(BaseEntity baseEntity) {
        baseEntity.createdTime = LocalDateTime.now();
//        baseEntity.updatedTime = LocalDateTime.now();
        //TODO add user id
        baseEntity.createdBy = 1;
//        baseEntity.updatedBy = 1;
    }

    @PreUpdate
    private void onPreUpdate(BaseEntity baseEntity) {
        //TODO add user id
        baseEntity.updatedTime = LocalDateTime.now();
        baseEntity.updatedBy = 1;
    }
}
