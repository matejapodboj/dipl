package com.example.lamps.controller;

import com.example.lamps.dto.OrderRequest;
import com.example.lamps.model.Order;
import com.example.lamps.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/orders")
    public Order createOrder(@RequestBody OrderRequest orderRequest) {
        Order order = new Order();

        // Set order fields using orderRequest data
        order.setFirstName(orderRequest.getFirstName());
        order.setLastName(orderRequest.getLastName());
        order.setEmail(orderRequest.getEmail());
        order.setPhone(orderRequest.getPhone());
        order.setAddress(orderRequest.getAddress());
        order.setCity(orderRequest.getCity());

        return orderService.createOrder(order, orderRequest.getProductIds());
    }
}
