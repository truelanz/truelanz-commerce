package com.truelanz.truelanzcommerce.entities;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class User {
    
    private Long id;
    private String name;
    private String email;
    private String phone;
    private LocalDate birthDate;
    private String password;

}
