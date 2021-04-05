package com.project.dto;

import com.project.entity.Company;
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
public class InvoiceDto {

    private long id;

    private String invoiceNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate invoiceDate;

    private VendorClientDto vendorClient;

    private double cost;

    private double tax = 0.9;

    private double total=cost*tax;

    private InvoiceProductDto invoiceProductDto;

    private ProductDto productDto;

    private CompanyDto companyDto;

    private Status status;






}
