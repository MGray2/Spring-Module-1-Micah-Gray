package com.example.springbootmodule1.order;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/order")
public class OrderController {

    private final OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) { this.orderService = orderService; }

    @GetMapping
    public List<Order> getOrders() { return orderService.getOrders(); }

    @GetMapping(path = "{orderId}")
    public Optional<Order> getOneOrder(@PathVariable("orderId") Long orderId) {
        return orderService.getOrderById(orderId);
    }

    @PostMapping
    public void registerNewOrder(@RequestBody Order order) { orderService.addNewOrder(order); }

    @DeleteMapping(path="{orderId}")
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        orderService.deleteOrder(orderId);
    }

    @Transactional
    @PutMapping(path="{orderId}")
    public void updateOrder(@PathVariable("orderId") Long orderId,
                            @RequestParam(required = false) String orderNumber,
                            @RequestParam(required = false) Long id) {
        orderService.updateOrder(orderId, orderNumber, id);
    }

}
