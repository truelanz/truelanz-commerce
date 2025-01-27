package com.truelanz.truelanzcommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truelanz.truelanzcommerce.entities.User;
import com.truelanz.truelanzcommerce.services.exceptions.ForbiddenException;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    //Se quem estiver acessando não for da 'ROLE_ADMIN', ou não for dona do pedido de acesso,
    //lançar um erro 403, de acesso negado.
    public void validateSelfOrdAdmin(long userID) {
        User me = userService.authenticated();
        //se o usuário autenticado não for admin nem o mesmo usuário do argumento
        if (!me.hasRole("ROLE_ADMIN") && !me.getId().equals(userID)) {
            throw new ForbiddenException("Access denied");
        }
    }
}
