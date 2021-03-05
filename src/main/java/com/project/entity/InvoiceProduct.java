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
public class InvoiceProduct extends BaseEntity{

    private double unitPrice;
    private double quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    private Invoice invoice;

    @OneToOne(cascade = CascadeType.ALL)
    private Product product;
}
