package com.project.dto;

import com.project.enums.ClientVendorType;
import com.project.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {

    private long Id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String phone;
    private RoleDto roleDto;
    private CompanyDto companyDto;
    private ClientVendorType type;


}
