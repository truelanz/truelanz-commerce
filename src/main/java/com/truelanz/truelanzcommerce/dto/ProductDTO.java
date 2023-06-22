package com.truelanz.truelanzcommerce.dto;

import com.truelanz.truelanzcommerce.entities.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProductDTO {

    private Long id;
    @Size(min = 3, max = 80, message = "field must be 3 to 80 characters long")
    @NotBlank(message = "Required field")
    private String name;
    @Size(min = 10, message = "The field must be at least 10 characters long")
    @NotBlank(message = "Required field")
    private String description;
    @Positive(message = "the field must be positive")
    private Double price;
    private String imgUrl;

    //Constructor para conversão de dados no ProductService ...
    public ProductDTO(Product entity) {
        id = entity.getId();
        name = entity.getName();
        description = entity.getDescription();
        price = entity.getPrice();
        imgUrl = entity.getImgUrl();
    }
}

