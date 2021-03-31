package com.project.dto;

import com.project.entity.Company;
import com.project.entity.Role;
import com.project.enums.ClientVendorType;
import com.project.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {

    private Long Id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String phone;

    private RoleDto role;
    private Company company;
    private ClientVendorType type;
    private Status status;


}
