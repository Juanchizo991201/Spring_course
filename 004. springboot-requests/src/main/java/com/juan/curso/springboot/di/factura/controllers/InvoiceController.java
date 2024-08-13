package com.juan.curso.springboot.di.factura.controllers;

import com.juan.curso.springboot.di.factura.models.Client;
import com.juan.curso.springboot.di.factura.models.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private final Invoice invoice;

    @Autowired
    public InvoiceController(Invoice invoice) {
        this.invoice = invoice;
    }

    @GetMapping("/show")
    public Invoice show() {

        Invoice i = new Invoice();
        Client c = new Client();

        c.setName(invoice.getClient().getName());
        c.setLastname(invoice.getClient().getLastname());

        i.setClient(c);
        i.setDescription(invoice.getDescription());
        i.setItems(invoice.getItems());

        return i;
    }

}
