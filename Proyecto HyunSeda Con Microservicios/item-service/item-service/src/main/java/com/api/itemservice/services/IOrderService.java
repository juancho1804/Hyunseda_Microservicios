package com.api.itemservice.services;

import com.api.itemservice.models.OrderModel;
import java.util.List;


public interface IOrderService {
    OrderModel createOrderClient(Long id,OrderModel order);
    List<OrderModel> getAllOrders();
    List<OrderModel>findOrdersByUserOfClients(String username);
    Long findMaxId();
}
