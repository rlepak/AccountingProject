package com.project.entity;

import com.project.enums.InvoiceType;
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

    private String invoiceNumber;
    private String invoiceStatus;

    @Enumerated(EnumType.STRING)
    private InvoiceType invoiceType;

    private LocalDate invoiceDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private SPTable spTable;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    private boolean enabled;

    @OneToMany(mappedBy = "invoice", fetch = FetchType.LAZY)
    private Set<InvoiceProduct> invoiceProducts = new HashSet<>();
}
