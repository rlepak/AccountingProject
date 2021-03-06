package com.project.dto;

import com.project.enums.Unit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDto {

    private long id;
    private String productName;
    private String description;
    private InvoiceProductDto invoiceProductDto;
    private Unit unitType;
    private String category;
    private boolean status;
    private double tax;


}
