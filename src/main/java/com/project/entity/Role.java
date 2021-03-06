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
public class Role{

        @Id
        int id;
        private String description;
        private boolean enabled;

        @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
        private Set<User> users = new HashSet<>();

}
