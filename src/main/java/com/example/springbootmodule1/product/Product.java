package com.example.springbootmodule1.product;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal price;


    public Long getId() {
        return this.id;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setPrice(BigDecimal newPrice) {
        this.price = newPrice;
    }

}
