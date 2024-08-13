package com.juan.curso.springboot.di.factura;

import com.juan.curso.springboot.di.factura.models.Item;
import com.juan.curso.springboot.di.factura.models.Product;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:data.properties")
public class AppConfig {

  @Bean("default")
  List<Item> itemsInvoice() {
    Product p1 = new Product("Camara Sony", 100.0);
    Product p2 = new Product("Stereo Samsung", 200.0);
    Product p3 = new Product("Iphone", 500.0);

    return List.of(new Item(p1, 2), new Item(p2, 4), new Item(p3, 1));
  }
}
