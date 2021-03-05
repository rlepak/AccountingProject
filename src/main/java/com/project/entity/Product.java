package com.project.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Where(clause = "is_deleted=false")
public class Product extends BaseEntity{

    private String name;
    private String description;
    private int quantity;
    private double price;
    private String unit;
    private double lowLimitAlert;
    private double tax;
    private boolean enabled;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @OneToOne(cascade = CascadeType.ALL)
    private InvoiceProduct invoiceProduct;

}
