package com.project.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Role{

        @Id
        Long id;
        private String description;
        private boolean enabled;

        @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
        private Set<User> users = new HashSet<>();



}
