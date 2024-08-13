package com.jjmontenegrop.springbootcrud.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @JsonIgnoreProperties({"roles", "handler", "hibernateLazyInitializer"})
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Role() {
        this.users = new ArrayList<>();
    }
}
