package com.truelanz.truelanzcommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.truelanz.truelanzcommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
