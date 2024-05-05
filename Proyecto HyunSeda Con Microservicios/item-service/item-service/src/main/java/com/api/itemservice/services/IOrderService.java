package com.api.itemservice.services;

import com.api.itemservice.models.OrderModel;

import java.util.List;

public interface IOrderService {
    public OrderModel crearOrder(OrderModel order);
    public List<OrderModel> listOrders();
}
