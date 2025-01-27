package com.truelanz.truelanzcommerce.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.truelanz.truelanzcommerce.dto.OrderDTO;
import com.truelanz.truelanzcommerce.dto.OrderItemDTO;
import com.truelanz.truelanzcommerce.entities.Order;
import com.truelanz.truelanzcommerce.entities.OrderItem;
import com.truelanz.truelanzcommerce.entities.OrderStatus;
import com.truelanz.truelanzcommerce.entities.Product;
import com.truelanz.truelanzcommerce.entities.User;
import com.truelanz.truelanzcommerce.repositories.OrderItemRepository;
import com.truelanz.truelanzcommerce.repositories.OrderRepository;
import com.truelanz.truelanzcommerce.repositories.ProductRepository;
import com.truelanz.truelanzcommerce.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AuthService authService;

    // buscar por ID: GET \\    
    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Resource not Found"));
        //Se quem estiver acessando não for da 'ROLE_ADMIN', ou não for dona do pedido de acesso,
        //lançar um erro 403, de acesso negado.
        authService.validateSelfOrdAdmin(order.getClient().getId());
        return new OrderDTO(order);
    }

    // insert: POST\\
    @Transactional
    public OrderDTO insert(OrderDTO dto) {
        Order order = new Order();

        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);

        User user = userService.authenticated();
        order.setClient(user);

        for (OrderItemDTO orderItemDTO : dto.getItems()) {
            Product product = productRepository.getReferenceById(orderItemDTO.getProductId());
            OrderItem item = new OrderItem(order, product, orderItemDTO.getQuantity(), product.getPrice());
            order.getItems().add(item);
        }
        orderRepository.save(order);
        orderItemRepository.saveAll(order.getItems());
        
        return new OrderDTO(order);
    }

}
