package com.jjmontenegrop.springbootjpa.entities;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PersonDTO {

    private String name;
    private String lastname;

    public PersonDTO(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

}
