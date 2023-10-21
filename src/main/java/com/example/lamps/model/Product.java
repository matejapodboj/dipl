package com.example.lamps.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "image", length = 1000)
    private String image; // This can be a URL or path to the image. Adjust length as needed.

    @Column(name = "short_description", length = 500)
    private String shortDescription;

    @Column(name = "long_description", length = 2000)
    private String longDescription;

    @Column(name = "width")
    private Double width;

    @Column(name = "height")
    private Double height;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders = new ArrayList<>();
    // Default constructor
    public Product() {}

    // Constructor with all fields
    public Product(String title, Double price, String image, String shortDescription, String longDescription, Double width, Double height) {
        this.title = title;
        this.price = price;
        this.image = image;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.width = width;
        this.height = height;
    }

    // Getter and Setter methods

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", longDescription='" + longDescription + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }

}
