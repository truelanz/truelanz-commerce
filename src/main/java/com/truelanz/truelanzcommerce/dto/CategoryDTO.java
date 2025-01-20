package com.truelanz.truelanzcommerce.dto;

import com.truelanz.truelanzcommerce.entities.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CategoryDTO {

    private Long id;
    private String name;
    
    public CategoryDTO(Category entity) {
        id = entity.getId();
        name = entity.getName();
    }
    

}
