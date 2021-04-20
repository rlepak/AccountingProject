package com.project.dto;

import com.project.entity.Company;
import com.project.enums.InvoiceType;
import com.project.enums.Status;
import com.project.repository.InvoiceProductRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
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

    private double tax = 0.09;

    private double total;

    private InvoiceProductDto invoiceProductDto;

    private ProductDto productDto;

    private CompanyDto companyDto;

    private Status status;

    private InvoiceType invoiceType;




}
