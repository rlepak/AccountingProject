package com.project.dto;

import com.project.entity.State;
import com.project.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CompanyDto {

    private long Id;
    private String title;
    private String state;
    private Status status;
    private String representative;
    private String email;
    private String address;
    private String phone;
    private String zip;
    private boolean enabled;
    private String successfulMessage;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate establishedDate;


}
