package com.example.springbootmodule1;

import com.example.springbootmodule1.customer.Customer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@SpringBootApplication
public class SpringBootModule1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootModule1Application.class, args);
    }


}
