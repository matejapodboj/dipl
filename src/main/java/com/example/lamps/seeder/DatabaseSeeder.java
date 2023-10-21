package com.example.lamps.seeder;

import com.example.lamps.model.Product;
import com.example.lamps.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final ProductRepository productRepository;

    @Autowired
    public DatabaseSeeder(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Check if the database is empty, and if so, seed some sample products.
        if (productRepository.count() == 0) {
            List<Product> products = new ArrayList<>();

            products.add(new Product("Lamp A", 50.0, "lamp_a.jpg", "Short desc for Lamp A", "Long desc for Lamp A", 10.0, 20.0));
            products.add(new Product("Lamp B", 60.0, "lamp_a.jpg", "Short desc for Lamp B", "Long desc for Lamp B", 12.0, 25.0));
            products.add(new Product("Lamp C", 60.0, "lamp_a.jpg", "Short desc for Lamp C", "Long desc for Lamp C", 12.0, 25.0));
            products.add(new Product("Lamp D", 60.0, "lamp_a.jpg", "Short desc for Lamp D", "Long desc for Lamp D", 12.0, 25.0));
            products.add(new Product("Lamp E", 60.0, "lamp_a.jpg", "Short desc for Lamp E", "Long desc for Lamp E", 12.0, 25.0));
            products.add(new Product("Lamp F", 60.0, "lamp_a.jpg", "Short desc for Lamp F", "Long desc for Lamp F", 12.0, 25.0));
            products.add(new Product("Lamp G", 60.0, "lamp_a.jpg", "Short desc for Lamp G", "Long desc for Lamp G", 12.0, 25.0));
            products.add(new Product("Lamp H", 60.0, "lamp_a.jpg", "Short desc for Lamp H", "Long desc for Lamp H", 12.0, 25.0));
            products.add(new Product("Lamp J", 60.0, "lamp_a.jpg", "Short desc for Lamp J", "Long desc for Lamp J", 12.0, 25.0));
            products.add(new Product("Lamp K", 60.0, "lamp_a.jpg", "Short desc for Lamp K", "Long desc for Lamp K", 12.0, 25.0));

            productRepository.saveAll(products);
        }
    }
}
