package com.truelanz.truelanzcommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.truelanz.truelanzcommerce.entities.OrderItem;
import com.truelanz.truelanzcommerce.entities.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {


}
