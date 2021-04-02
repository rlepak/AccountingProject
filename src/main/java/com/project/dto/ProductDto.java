package com.project.dto;

import com.project.entity.Category;
import com.project.enums.Status;
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
    private int quantity;
//    private InvoiceProductDto invoiceProductDto;
    private Unit unit;
    private Category category;
    private Status status;
    private double tax;


}
