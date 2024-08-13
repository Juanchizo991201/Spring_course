package com.juan.curso.springboot.di.factura.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Item {

    private Product product;
    private Integer quantity;

    public Double getTotal() {
        return product.getPrice() * quantity;
    }
}
