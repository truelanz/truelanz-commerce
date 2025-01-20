package com.truelanz.truelanzcommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truelanz.truelanzcommerce.dto.UserDTO;
import com.truelanz.truelanzcommerce.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    // buscar informações de usuário logado \\
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')") 
    @GetMapping(value = "/me")
    public ResponseEntity<UserDTO> getMe() {
        UserDTO dto = userService.getMe();
        return ResponseEntity.ok(dto);
    }

}
