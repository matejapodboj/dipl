package com.example.lamps.controller;

import com.example.lamps.model.Product;
import com.example.lamps.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<Map<String, Object>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "asc") String sortOrder,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Double priceFrom, @RequestParam(required = false) Double priceTo,
            @RequestParam(required = false) Double widthFrom, @RequestParam(required = false) Double widthTo,
            @RequestParam(required = false) Double heightFrom, @RequestParam(required = false) Double heightTo
    ) {
        Sort sort = Sort.by(sortOrder.equals("asc") ? Sort.Order.asc("price") : Sort.Order.desc("price"));
        PageRequest pageableRequest = PageRequest.of(page, pageSize, sort);
        Page<Product> products = productService.getAllProducts(title, priceFrom, priceTo, widthFrom, widthTo, heightFrom, heightTo, pageableRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("content", products.getContent());
        response.put("totalPages", products.getTotalPages());
        response.put("totalElements", products.getTotalElements());
        response.put("currentPage", products.getNumber());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.of(productService.getProductById(id));
    }
}

