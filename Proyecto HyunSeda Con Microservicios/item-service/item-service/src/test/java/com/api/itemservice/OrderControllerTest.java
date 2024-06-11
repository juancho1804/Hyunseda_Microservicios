package com.api.itemservice;

import com.api.itemservice.controllers.OrderController;
import com.api.itemservice.models.OrderModel;
import com.api.itemservice.services.IOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OrderControllerTest {

    @Mock
    private IOrderService orderService;

    @InjectMocks
    private OrderController orderController;

    /**
     * @brief Método de configuración que inicializa los objetos simulados.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * @brief Método de prueba para {@link OrderController#createOrderClient(Long, OrderModel)}.
     *
     * Este test simula la creación de una nueva orden y verifica que el objeto OrderModel devuelto sea el correcto.
     *
     * @throws Exception si ocurre algún error durante la prueba.
     */
    @Test
    void createOrderClient() {
        // Given
        Long id = 1L;
        OrderModel order = new OrderModel();

        // When
        when(orderService.createOrderClient(id, order)).thenReturn(order);

        // Then
        OrderModel result = orderController.createOrderClient(id, order);
        assertEquals(order, result);

        verify(orderService, times(1)).createOrderClient(id, order);
    }

    /**
     * @brief Método de prueba para {@link OrderController#getAllOrders()}.
     *
     * Este test simula una solicitud GET a la ruta "/orders" y verifica que la respuesta tenga el estado HTTP y el cuerpo correctos.
     *
     * @throws Exception si ocurre algún error durante la prueba.
     */
    @Test
    void getAllOrders() {
        // Given
        OrderModel order1 = new OrderModel();
        OrderModel order2 = new OrderModel();
        List<OrderModel> orders = Arrays.asList(order1, order2);

        // When
        when(orderService.getAllOrders()).thenReturn(orders);

        // Then
        List<OrderModel> result = orderController.getAllOrders();
        assertEquals(orders, result);

        verify(orderService, times(1)).getAllOrders();
    }

    /**
     * @brief Método de prueba para {@link OrderController#findOrdersByUserOfClients(String)}.
     *
     * Este test simula una solicitud GET a la ruta "/orders/byUsername/{username}" y verifica que la respuesta tenga el estado HTTP y el cuerpo correctos.
     *
     * @throws Exception si ocurre algún error durante la prueba.
     */
    @Test
    void findOrdersByUserOfClients() {
        // Given
        String username = "testUser";
        OrderModel order1 = new OrderModel();
        OrderModel order2 = new OrderModel();
        List<OrderModel> orders = Arrays.asList(order1, order2);

        // When
        when(orderService.findOrdersByUserOfClients(username)).thenReturn(orders);

        // Then
        List<OrderModel> result = orderController.findOrdersByUserOfClients(username);
        assertEquals(orders, result);

        verify(orderService, times(1)).findOrdersByUserOfClients(username);
    }
}