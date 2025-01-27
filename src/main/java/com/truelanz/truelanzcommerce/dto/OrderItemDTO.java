package com.truelanz.truelanzcommerce.dto;

import com.truelanz.truelanzcommerce.entities.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {

    private Long productId;
    private Integer quantity;
    private Double price;
    private String name;
    private String imgUrl;

    public OrderItemDTO (OrderItem entity) {
        productId = entity.getProduct().getId();
        quantity = entity.getQuantity();
        price = entity.getPrice();
        name = entity.getProduct().getName();
        imgUrl = entity.getProduct().getImgUrl();
    }

    //Calcular subtotal dos itens adicionados;
    public Double getSubTotal() {
        return price * quantity;
    }
}
