package com.project.dto;

import com.project.entity.Invoice;
import com.project.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InvoiceProductDto {

    private long Id;
    private Product product;
    private Invoice invoice;
    private double unitPrice;
    private double quantity;
    private double total;



}
