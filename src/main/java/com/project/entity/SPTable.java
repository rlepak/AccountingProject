package com.project.entity;

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
public class SPTable extends BaseEntity{

    private String companyName;
    private String phone;
    private String email;
    private String type;
    private String zipCode;
    private String address;
    private String state;
    private boolean enabled;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @OneToMany(mappedBy = "spTable",fetch = FetchType.LAZY)
    private Set<Invoice> invoices = new HashSet<>();


}
