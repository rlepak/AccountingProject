package com.project.dto;

import com.project.entity.State;
import com.project.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VendorClientDto {

    private String companyName;
    private String zipCode;
    private String phone;
    private String address;
    private String email;
    private State state;
    private String type;
    private Status status;


}
