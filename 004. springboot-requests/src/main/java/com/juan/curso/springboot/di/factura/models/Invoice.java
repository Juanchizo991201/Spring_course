package com.juan.curso.springboot.di.factura.models;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@Component
@RequestScope
//@JsonIgnoreProperties({"targetSource", "advisors", })
@NoArgsConstructor
@Getter
@Setter
public class Invoice {

    // Atributos
    @Value("${invoice.description}")
    private String description;
    private List<Item> items;
    private Client client;
    // Inyeccion de dependencias (Client) por constructor
    @Autowired
    public Invoice(Client client) {
        this.client = client;
        System.out.println("Initializing invoice creation for: " + client.getName());
    }

    @PostConstruct
    public void init() {
        client.setName(client.getName().concat(" ").concat("Lopez"));

        description = description.concat(" for: ").concat(client.getName());
        System.out.println(description);

        System.out.println("Finalizing invoice creation for: " + client.getName());

    }

    @PreDestroy
    public void destroy() {
        System.out.println("Invoice destroyed: ".concat(description));
    }

    @Autowired
    public void setItems(@Qualifier("default") List<Item> items) {
        this.items = items;
    }

    public Double getTotal() {
        return items.stream().mapToDouble(Item::getTotal).sum();
    }

}
