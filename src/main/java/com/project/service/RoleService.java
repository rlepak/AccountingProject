package com.project.service;

import com.project.dto.RoleDto;
import com.project.entity.Role;

import java.util.List;

public interface RoleService {

    RoleDto findById(Long id);
    List<RoleDto> findAllRoles();
}
