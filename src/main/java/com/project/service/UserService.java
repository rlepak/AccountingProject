package com.project.service;

import com.project.dto.UserDto;
import com.project.exception.AccountingProjectException;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserService {

    List<UserDto> findAllUsers();

    UserDto save(UserDto userDto) throws AccountingProjectException;
    UserDto findById(Long id) throws AccountingProjectException;
    UserDto update (UserDto userDto) throws AccountingProjectException;
    UserDto findByEmail(String email) throws AccountingProjectException;
    void deleteUser(String email) throws AccountingProjectException;


}
