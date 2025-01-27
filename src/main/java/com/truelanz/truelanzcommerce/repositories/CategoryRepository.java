package com.truelanz.truelanzcommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.truelanz.truelanzcommerce.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}

