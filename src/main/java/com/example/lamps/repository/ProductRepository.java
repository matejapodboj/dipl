package com.example.lamps.repository;

import com.example.lamps.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE " +
            "(:title IS NULL OR p.title LIKE %:title%) AND " +
            "(:priceFrom IS NULL OR p.price >= :priceFrom) AND " +
            "(:priceTo IS NULL OR p.price <= :priceTo) AND " +
            "(:widthFrom IS NULL OR p.width >= :widthFrom) AND " +
            "(:widthTo IS NULL OR p.width <= :widthTo) AND " +
            "(:heightFrom IS NULL OR p.height >= :heightFrom) AND " +
            "(:heightTo IS NULL OR p.height <= :heightTo)")
    Page<Product> findWithFilters(
            @Param("title") String title,
            @Param("priceFrom") Double priceFrom,
            @Param("priceTo") Double priceTo,
            @Param("widthFrom") Double widthFrom,
            @Param("widthTo") Double widthTo,
            @Param("heightFrom") Double heightFrom,
            @Param("heightTo") Double heightTo,
            Pageable pageable
    );
}
