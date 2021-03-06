package com.project.dto;

import com.project.entity.Company;
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
public class InvoiceDto {

    private String invoiceNumber;

    @DateTimeFormat(pattern = "MM-dd-yyyy")
    private LocalDate invoiceDate;

    private InvoiceProductDto invoiceProductDto;

    private Company company;


}
