package com.example.springbootmodule1.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CustomerConfig {
    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository repository) {
        return args -> {
            Customer jordan = new Customer(
                    "Jordan",
                    "Donahue",
                    "jdonahue@gmail.com"
            );
            Customer ab = new Customer(
                    "Ab",
                    "McCullough",
                    "acculough@gmail.com"
            );
            repository.saveAll(List.of(jordan, ab));
        };
    }
}
