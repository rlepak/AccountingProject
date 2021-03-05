package com.project.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Where(clause = "is_deleted=false")
public class Invoice extends BaseEntity {

    private long invoiceNumber;
    private String invoiceStatus;
    private String invoiceType;
    private LocalDate invoiceDate;

    @OneToOne(cascade = CascadeType.ALL)
    private SPTable spTable;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    private boolean enabled;

    @OneToMany(mappedBy = "invoice", fetch = FetchType.LAZY)
    private Set<InvoiceProduct> invoiceProducts = new HashSet<>();
}
