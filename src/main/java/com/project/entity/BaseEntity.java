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
@EntityListeners(BaseEntityListener.class)
public class BaseEntity {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, updatable = false)
    public int createdBy;

    @Column(nullable = false, updatable = false)
    public LocalDateTime createdTime;

    @Column(nullable = false)
    public int updatedBy;

    public LocalDateTime updatedTime;

    public Boolean isDeleted = false;




}
