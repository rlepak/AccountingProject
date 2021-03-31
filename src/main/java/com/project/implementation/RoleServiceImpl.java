package com.project.implementation;

import com.project.dto.RoleDto;
import com.project.entity.Role;
import com.project.entity.User;
import com.project.repository.RolesRepository;
import com.project.service.RoleService;
import com.project.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private RolesRepository rolesRepository;
    private MapperUtil mapperUtil;

    public RoleServiceImpl(RolesRepository rolesRepository, MapperUtil mapperUtil) {
        this.rolesRepository = rolesRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public RoleDto findById(Long id) {
        Optional<Role> role = rolesRepository.findById(id);
        return mapperUtil.convert(role, new RoleDto());
    }

    @Override
    public List<RoleDto> findAllRoles() {
        List<Role> allRoles = rolesRepository.findAll();
        return allRoles.stream().map(obj -> mapperUtil.convert(obj, new RoleDto())).collect(Collectors.toList());
    }

}
