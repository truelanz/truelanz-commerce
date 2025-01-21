package com.truelanz.truelanzcommerce.dto;

import java.time.Instant;

import com.truelanz.truelanzcommerce.entities.Payment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PaymentDTO {
    
    private Long id;
    private Instant moment;

    public PaymentDTO (Payment entity) {
        id = entity.getId();
        moment = entity.getMoment();
    }
}
