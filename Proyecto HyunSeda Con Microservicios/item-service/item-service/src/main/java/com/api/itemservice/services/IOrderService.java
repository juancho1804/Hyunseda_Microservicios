package com.api.itemservice.services;

import com.api.itemservice.models.ClientModel;
import com.api.itemservice.models.OrderModel;

import java.util.List;
import java.util.Optional;

public interface IOrderService {
    OrderModel crearOrder(OrderModel order);
    List<OrderModel> listOrders();
    OrderModel crearOrderCliente(Long id,OrderModel order);
    ClientModel findClientById(Long id);

    Optional<OrderModel> findByClientId(Long id);
    List<ClientModel> findClientsByUsername(String username);
    List<OrderModel>findOrdersByUserOfClients(String username);
    Long getMaxOrderId();
}
