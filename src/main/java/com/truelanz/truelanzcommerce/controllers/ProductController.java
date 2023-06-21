package com.truelanz.truelanzcommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truelanz.truelanzcommerce.dto.ProductDTO;
import com.truelanz.truelanzcommerce.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    // bucar lista COMPLETA de elementos \\
    /* @GetMapping
    public List<ProductDTO> findAll() {
        return productService.findAll();
    } */

    // bucar lista PAGINADA de elementos \\
    // /products?size=5&page=0&sort=name -> QUERY PARAMS, tamanho=, página=, ordenar por= ...
    @GetMapping
    public Page<ProductDTO> findAll(Pageable pageable) {
        return productService.findAll(pageable);
    }
    
    // buscar por ID \\
    @GetMapping(value = "/{id}")
    public ProductDTO findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    

}
