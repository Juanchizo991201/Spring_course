package com.jjmontenegrop.springbootjpa.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "persons")
public class Person {

    @Embedded
    private Audit audit;

    @Id // This annotation is used to define the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // This annotation is used to define the strategy to generate the primary key
    private int id;

    private String name;
    private String lastname;

    @Column(name = "programming_language") //
    private String programmingLanguage;

    public Person(int i, String name, String lastName, String programmingLanguage) {
        this.id = i;
        this.name = name;
        this.lastname = lastName;
        this.programmingLanguage = programmingLanguage;
    }
}
