package com.juan.curso.springboot.di.factura.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@NoArgsConstructor
@RequestScope
//@JsonIgnoreProperties({"targetSource", "advisors", })
@Getter
@Setter
public class Client {

    @Value("${client.name}")
    private String name;
    @Value("${client.lastname}")
    private String lastname;


}
