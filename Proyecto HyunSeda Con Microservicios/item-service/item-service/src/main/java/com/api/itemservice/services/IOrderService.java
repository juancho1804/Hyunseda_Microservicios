package com.api.itemservice.services;

import com.api.itemservice.models.OrderModel;
import java.util.List;


public interface IOrderService {

    /**
     * @brief Método para crear una orden para un cliente.
     *
     * Este método se utiliza para crear una nueva orden para un cliente en la base de datos.
     *
     * @param id El ID del cliente.
     * @param order La orden a crear.
     * @return La orden creada.
     */
    OrderModel createOrderClient(Long id,OrderModel order);

    /**
     * @brief Método para obtener todas las órdenes.
     *
     * Este método se utiliza para obtener una lista de todas las órdenes en la base de datos.
     *
     * @return Una lista de todas las órdenes.
     */
    List<OrderModel> getAllOrders();

    /**
     * @brief Método para buscar órdenes por el nombre de usuario del cliente.
     *
     * Este método se utiliza para buscar órdenes en la base de datos por el nombre de usuario del cliente.
     *
     * @param username El nombre de usuario del cliente.
     * @return Una lista de órdenes que pertenecen al cliente.
     */
    List<OrderModel>findOrdersByUserOfClients(String username);

    /**
     * @brief Método para encontrar el ID más grande entre todas las órdenes.
     *
     * Este método se utiliza para encontrar el ID más grande entre todas las órdenes en la base de datos.
     *
     * @return El ID más grande entre todas las órdenes.
     */
    Long findMaxId();
}
