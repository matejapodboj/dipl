package com.example.lamps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ContactUsService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private Environment env;

    public void sendMessage(String name, String email, String messageText) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(env.getProperty("sender.email"));
        message.setTo(env.getProperty("contact.email"));
        message.setSubject("Nova poruka od: " + name);
        message.setCc(email);
        message.setText(messageText);
        mailSender.send(message);
    }
}
