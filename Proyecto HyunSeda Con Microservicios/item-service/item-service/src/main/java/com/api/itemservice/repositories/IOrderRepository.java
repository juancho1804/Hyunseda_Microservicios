package com.api.itemservice.repositories;

import com.api.itemservice.models.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<OrderModel, Long> {
}
