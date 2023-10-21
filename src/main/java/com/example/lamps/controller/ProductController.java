package com.example.lamps.controller;

import com.example.lamps.model.Product;
import com.example.lamps.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private static final int DEFAULT_PAGE_SIZE = 10;

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(defaultValue = "0") int page) {
        Page<Product> productPage = productService.getAllProducts(PageRequest.of(page, DEFAULT_PAGE_SIZE));
        return ResponseEntity.ok(productPage.getContent());  // Convert Page<Product> to ResponseEntity<List<Product>>
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.of(productService.getProductById(id));
    }
}

