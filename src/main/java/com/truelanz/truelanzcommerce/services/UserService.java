package com.truelanz.truelanzcommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.truelanz.truelanzcommerce.dto.UserDTO;
import com.truelanz.truelanzcommerce.entities.Role;
import com.truelanz.truelanzcommerce.entities.User;
import com.truelanz.truelanzcommerce.projections.UserDetailsProjection;
import com.truelanz.truelanzcommerce.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    //Obtendo query e autorização de login
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetailsProjection> result = userRepository.searchUserAndRolesByEmail(username);
        if(result.size() == 0)
            throw new UsernameNotFoundException(("User not found"));
        
        User user = new User();
        user.setEmail(username);
        user.setPassword(result.get(0).getPassword());
        for(UserDetailsProjection projection : result) {
            user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
        }

        return user;
    }

    //Obtendo usuário logado
    protected User authenticated() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
            String username = jwtPrincipal.getClaim("username");
    
           return userRepository.findByEmail(username).get();
        }
        catch (Exception e) {
            throw new UsernameNotFoundException("Email not Found");
        }
    }

    //Obtendo Usuário atravéz do userDTO
    @Transactional(readOnly = true)
    public UserDTO getMe() {
        User user = authenticated();
        return new UserDTO(user);
    }

}

