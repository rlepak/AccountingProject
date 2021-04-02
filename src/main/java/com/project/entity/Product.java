package com.project.entity;

import com.project.enums.Status;
import com.project.enums.Unit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @Enumerated(EnumType.STRING)
    private Unit unit;

    private boolean lowLimitAlert;
    private double tax;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY)
    private Set<InvoiceProduct> invoiceProducts = new HashSet<>();

}
