package com.example.lamps.service;

import com.example.lamps.model.Order;
import com.example.lamps.model.Product;
import com.example.lamps.repository.OrderRepository;
import com.example.lamps.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private JavaMailSender emailSender;

    public Order createOrder(Order order, List<Long> productIds) {
        List<Product> products = productRepository.findAllById(productIds);

        order.setProducts(products);

        Order savedOrder = orderRepository.save(order);
        sendOrderConfirmationEmail(savedOrder);
        return savedOrder;
    }

    private void sendOrderConfirmationEmail(Order order) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(order.getEmail());
        message.setSubject("Order Confirmation");
        message.setText("Thank you for your order!");

        emailSender.send(message);
    }

}
