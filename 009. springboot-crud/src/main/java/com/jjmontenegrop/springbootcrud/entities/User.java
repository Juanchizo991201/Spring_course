package com.jjmontenegrop.springbootcrud.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    @NotBlank
    @Size(min = 4, max = 12)
    private String username;

    @NotBlank
//    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @JsonIgnoreProperties({"users", "handler", "hibernateLazyInitializer"})
    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "role_id"})
    )
    private List<Role> roles;

    @Column(columnDefinition = "boolean default true")
    private Boolean enabled;

    @JsonIgnore
    @Transient
    private boolean admin;

    public User() {
        roles = new ArrayList<>();
    }

    @PrePersist
    protected void onCreate() {
        if (enabled == null) {
            enabled = true;
        }
    }
}
