package com.example.lamps.service;

import com.example.lamps.model.Order;
import com.example.lamps.model.Product;
import com.example.lamps.repository.OrderRepository;
import com.example.lamps.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private Environment env;

    public Order createOrder(Order order, List<Long> productIds) {
        List<Product> products = productRepository.findAllById(productIds);

        order.setProducts(products);

        Order savedOrder = orderRepository.save(order);
        sendOrderConfirmationEmail(savedOrder);
        return savedOrder;
    }

    private void sendOrderConfirmationEmail(Order order) {
        try {
            Context context = new Context();
            context.setVariable("order", order);

            // Process the Thymeleaf template and generate the email's HTML content
            String emailContent = templateEngine.process("orderConfirmation", context);

            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(order.getEmail());
            helper.setFrom(env.getProperty("sender.email"));
            helper.setSubject("Order Confirmation");
            helper.setText(emailContent, true);

            emailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
