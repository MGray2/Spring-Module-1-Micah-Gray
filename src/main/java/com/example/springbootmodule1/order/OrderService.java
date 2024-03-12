package com.example.springbootmodule1.order;

import com.example.springbootmodule1.customer.Customer;
import com.example.springbootmodule1.customer.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    public List<Order> getOrders() { return orderRepository.findAll(); }

    public Order getOrderById(Long orderId) { return orderRepository.getReferenceById(orderId); }

    public void addNewOrder(Order order) {
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
        Order order = orderRepository.getReferenceById(orderId);
        Customer orderCustomer = customerRepository.getReferenceById(order.getCustomerId());

        if (orderNumber != null && !orderNumber.isEmpty() && !Objects.equals(order.getId(), orderId)) {
            order.setOrderNumber(orderNumber);
        }
        if (customerId != null && !Objects.equals(order.getCustomerId(), customerId)) {
            order.setCustomer(orderCustomer);
        }
    }
}