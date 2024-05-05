package com.api.itemservice.services;

import com.api.itemservice.models.OrderModel;
import com.api.itemservice.repositories.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {
    @Autowired
    IOrderRepository orderRepository;

    public OrderModel crearOrder(OrderModel order) {
        return orderRepository.save(order);
    }
    public List<OrderModel> listOrders() {
        return orderRepository.findAll();
    }
}
