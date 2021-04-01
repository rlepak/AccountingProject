package com.project.dto;

import com.project.entity.State;
import com.project.entity.VendorClient;
import com.project.enums.ClientVendorType;
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

    private long Id;
    private String companyName;
    private String zipCode;
    private String phone;
    private String address;
    private String email;
    private String state;

    private ClientVendorType type;
    private Status status;


}
