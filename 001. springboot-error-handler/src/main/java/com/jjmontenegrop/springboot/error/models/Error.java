package com.jjmontenegrop.springboot.error.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Error {

    private String message;
    private String error;
    private int status;
    private Date date;

}
