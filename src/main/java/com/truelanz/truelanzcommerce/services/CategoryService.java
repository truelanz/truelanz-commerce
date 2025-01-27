package com.truelanz.truelanzcommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.truelanz.truelanzcommerce.dto.CategoryDTO;
import com.truelanz.truelanzcommerce.entities.Category;
import com.truelanz.truelanzcommerce.repositories.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // bucar lista PAGINADA e busca pelo nome de elementos: GET \\
    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        List<Category> result = categoryRepository.findAll();
        return result.stream().map(x -> new CategoryDTO(x)).toList();
    }

}
