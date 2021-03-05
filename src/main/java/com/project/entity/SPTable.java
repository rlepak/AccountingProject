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

    @OneToOne(mappedBy = "spTable")
    private Invoice invoice;


}
