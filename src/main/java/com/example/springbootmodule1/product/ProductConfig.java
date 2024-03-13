package com.example.springbootmodule1.product;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("product")
public class ProductConfig {

    @Bean
    CommandLineRunner commandLineRunner2(ProductRepository repository) {
        return args -> {
            Product gSoda = new Product("Green Soda", 2.00);
            Product water = new Product("Water", 1.00);
            Product shoes = new Product ("Nike Shoes", 60.00);
            Product car = new Product ("Little Tike's Cozy Coupe", 54.99);
            Product computer = new Product ("Acer Chromebook Enterprise Spin", 1332.99);
            Product bgloves = new Product ("Boxing Gloves", 19.99);
            repository.saveAll(List.of(gSoda, water, shoes, car, computer, bgloves));
        };
    }
}
