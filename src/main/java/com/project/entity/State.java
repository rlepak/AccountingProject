package com.project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class State{



    @Id()
    private String code;
    private String name;

//    @OneToMany(mappedBy = "state", cascade = CascadeType.ALL)
//    private Set<Company> company = new HashSet<>();
//
//    @OneToMany(mappedBy = "state", cascade = CascadeType.ALL)
//    private Set<VendorClient> vendorClients = new HashSet<>();

}
