package com.project.entity;

import com.project.enums.Status;
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
public class Company extends BaseEntity{

    private String title;

    private String address;


    private String state;

    private String zip;

    private String representative;

    @Column(unique = true)
    private String email;


    private LocalDate establishedDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String phone;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Product> products = new HashSet<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<VendorClient> vendorClients = new HashSet<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Invoice> invoices = new HashSet<>();
}
