package com.project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class BaseEntity {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @Column(nullable = false, updatable = false)
//    private int createdBy;
//
//    @Column(nullable = false, updatable = false)
//    private LocalDateTime createdTime;
//
//    @Column(nullable = false)
//    private int updatedBy;
//
//    private LocalDateTime updatedTime;
//
//    private Boolean isDeleted = false;

//    @PrePersist
//    private void onPrePersist() {
//        this.createdTime = LocalDateTime.now();
//        this.updatedTime = LocalDateTime.now();
//        //TODO add user id
//        this.createdBy = 1;
//        this.updatedBy = 1;
//    }
//
//    @PreUpdate
//    private void onPreUpdate() {
//        //TODO add user id
//        this.updatedTime = LocalDateTime.now();
//        this.updatedBy = 1;
//    }


}
