package com.example.springbootmodule1.order;

import com.example.springbootmodule1.customer.Customer;
import com.example.springbootmodule1.customer.CustomerRepository;
import com.example.springbootmodule1.product.Product;
import com.example.springbootmodule1.product.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    public void addNewOrder(Order order) {
        orderRepository.save(order);
    }

    public void addProductToOrder(Long orderId, Long productId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new IllegalStateException("Could not find Order."));
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalStateException("Could not find Order."));
        order.addProduct(product);
        orderRepository.save(order);
    }

    public void deleteOrder(Long orderId) {
        boolean exists = orderRepository.existsById(orderId);
        if (!exists) {
            throw new IllegalStateException("Order by the id of (" + orderId + ") does not exist!");
        }
        orderRepository.deleteById(orderId);
    }

    @Transactional
    public void updateOrder(Long orderId, String orderNumber, Long customerId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalStateException("Order with id of (" + orderId + ") does not exist!"));

        if (orderNumber != null && !orderNumber.isEmpty()) {
            order.setOrderNumber(orderNumber);
        }
        if (customerId != null) {
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new IllegalStateException("Customer with id (" + customerId + ") does not exist!"));
            order.setCustomer(customer);
        }
    }
}