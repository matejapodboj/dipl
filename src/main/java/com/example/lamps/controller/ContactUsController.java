package com.example.lamps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.lamps.dto.ContactUsRequest;
import com.example.lamps.service.ContactUsService;

@RestController
public class ContactUsController {

    @Autowired
    private ContactUsService contactUsService;

    @PostMapping("/contact-us")
    public ResponseEntity<String> sendEmail(@RequestBody ContactUsRequest request) {
        contactUsService.sendMessage(request.getName(), request.getEmail(), request.getMessage());
        return ResponseEntity.ok("Message sent successfully.");
    }
}
