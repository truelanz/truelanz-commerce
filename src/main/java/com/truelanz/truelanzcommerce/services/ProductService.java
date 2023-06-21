package com.truelanz.truelanzcommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.truelanz.truelanzcommerce.dto.ProductDTO;
import com.truelanz.truelanzcommerce.entities.Product;
import com.truelanz.truelanzcommerce.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // bucar lista COMPLETA de elementos \\
    /* @Transactional(readOnly = true)
    public List<ProductDTO> findAll() {
        List<Product> result = productRepository.findAll();
        return result.stream().map(x -> new ProductDTO(x)).toList();
    } */

    // bucar lista PAGINADA de elementos \\
    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> result = productRepository.findAll(pageable);
        return result.map(x -> new ProductDTO(x));
    }

     // buscar por ID \\
    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id).get();
        return new ProductDTO(product);
    }
}
