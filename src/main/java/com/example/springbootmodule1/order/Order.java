package com.example.springbootmodule1.order;

import com.example.springbootmodule1.customer.Customer;
import com.example.springbootmodule1.product.Product;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNumber;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToMany
    @JoinTable(
            name = "order_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<>();

    public Long getId() {
        return this.id;
    }

    public String getOrderNumber() { return this.orderNumber; }

    public List<Product> getProducts() { return this.products; }

    public Long getCustomerId() {
        return this.customer.getId();
    }

    public void setOrderNumber(String newNumber) {
        this.orderNumber = newNumber;
    }

    public void setCustomer(Customer newCustomer) {
        this.customer = newCustomer;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

}
