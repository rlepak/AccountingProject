package com.project.repository;

import com.project.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {


    State findByCode(String code);
}
