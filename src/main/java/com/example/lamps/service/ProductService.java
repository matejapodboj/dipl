package com.example.lamps.service;

import com.example.lamps.model.Product;
import com.example.lamps.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Page<Product> getAllProducts(String title, Double priceFrom, Double priceTo, Double widthFrom, Double widthTo, Double heightFrom, Double heightTo, Pageable pageable) {
        return productRepository.findWithFilters(title, priceFrom, priceTo, widthFrom, widthTo, heightFrom, heightTo, pageable);
    }


    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
}
