package com.truelanz.truelanzcommerce.dto;

import com.truelanz.truelanzcommerce.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ClientDTO {

    private Long id;
    private String name;

    public ClientDTO(User entity) {
        id = entity.getId();
        name = entity.getName();
    }
}
