package com.truelanz.truelanzcommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.truelanz.truelanzcommerce.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {


}
