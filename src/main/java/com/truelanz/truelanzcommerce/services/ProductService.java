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

    // bucar lista PAGINADA de elementos: GET \\
    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> result = productRepository.findAll(pageable);
        return result.map(x -> new ProductDTO(x));
    }

     // buscar por ID: GET \\
    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id).get();
        return new ProductDTO(product);
    }

     // insert: POST \\
     @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product();
        copyDtoToEntity(dto, entity);
        entity = productRepository.save(entity);
        return new ProductDTO(entity);
    }

     // update: PUT \\
     @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        Product entity = productRepository.getReferenceById(id);
        copyDtoToEntity(dto, entity);
        entity = productRepository.save(entity);
        return new ProductDTO(entity);
    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());
    }
}
