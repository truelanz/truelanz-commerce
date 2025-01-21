package com.truelanz.truelanzcommerce.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.truelanz.truelanzcommerce.entities.Order;
import com.truelanz.truelanzcommerce.entities.OrderItem;
import com.truelanz.truelanzcommerce.entities.OrderStatus;

import lombok.Getter;

@Getter
public class OrderDTO {

    private Long id;
    private Instant moment;
    private OrderStatus status;
    private PaymentDTO payment;

    private ClientDTO client;
    private List<OrderItemDTO> items = new ArrayList<>();

    public OrderDTO(Long id, Instant moment, OrderStatus status, PaymentDTO payment, ClientDTO client) {
        this.id = id;
        this.moment = moment;
        this.status = status;
        this.client = client;
        this.payment = payment;
    }

    public OrderDTO(Order entity) {
        id = entity.getId();
        moment = entity.getMoment();
        status = entity.getStatus();
        client = new ClientDTO(entity.getClient());
        payment = (entity.getPayment() == null) ? null : new PaymentDTO(entity.getPayment());
        for (OrderItem item : entity.getItems()) {
            OrderItemDTO itemDTO = new OrderItemDTO(item);
            items.add(itemDTO);
        }
    }

    //Calcular preço total do carrinho
    public Double getTotal() {
        double sum = 0.0;
        for (OrderItemDTO item : items) {
            sum += item.getSubTotal();
        }
        return sum;
    }

}
