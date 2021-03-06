package com.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CompanyDto {

    private long Id;
    private String title;
    private String representative;
    private String email;
    private String phone;
    private String zip;
    private String state;
    private boolean enabled;


}
