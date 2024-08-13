package com.jjmontenegrop.springbootjpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Embeddable
public class Audit {

    @Column(name = "created_at")
    private LocalDateTime createddAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist(){
        System.out.println("Before saving the object");
        createddAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        System.out.println("Before updating the object");
        updatedAt = LocalDateTime.now();
    }
}
