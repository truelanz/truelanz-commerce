package com.truelanz.truelanzcommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.truelanz.truelanzcommerce.dto.OrderDTO;
import com.truelanz.truelanzcommerce.entities.Order;
import com.truelanz.truelanzcommerce.repositories.OrderRepository;
import com.truelanz.truelanzcommerce.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // buscar por ID: GET \\
    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Resource not Found"));
        return new OrderDTO(order);
    }

}
