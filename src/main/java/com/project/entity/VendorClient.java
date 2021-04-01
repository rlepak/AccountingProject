package com.project.entity;

import com.project.enums.ClientVendorType;
import com.project.enums.Status;
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
public class VendorClient extends BaseEntity{

    private String companyName;
    private String phone;
    private String email;

    @Enumerated(EnumType.STRING)
    private ClientVendorType type;
    private String zipCode;
    private String address;

    private String state;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @OneToMany(mappedBy = "vendorClient",fetch = FetchType.LAZY)
    private Set<Invoice> invoices = new HashSet<>();


}
