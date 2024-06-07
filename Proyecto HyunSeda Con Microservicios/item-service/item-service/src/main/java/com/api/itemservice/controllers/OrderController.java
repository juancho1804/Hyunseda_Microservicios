package com.api.itemservice.controllers;

import com.api.itemservice.models.ClientModel;
import com.api.itemservice.models.ItemModel;
import com.api.itemservice.models.OrderModel;
import com.api.itemservice.services.IOrderService;
import com.api.itemservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PostMapping
    public OrderModel createProduct(@RequestBody OrderModel order) {
        return this.orderService.crearOrder(order);
    }
    @GetMapping
    public List<OrderModel> getAllOrders() {
        return  orderService.listOrders();
    }
    @GetMapping(path = "/client/{id}")
    public ClientModel findClient(@PathVariable("id") Long id) {
        return  orderService.findClientById(id);
    }

    @PostMapping(path = "/{id}")
    public OrderModel createOrderClient(@PathVariable("id") Long id, @RequestBody OrderModel order){
        return this.orderService.crearOrderCliente(id, order);
    }
    @GetMapping("/clientId/{id}")
    public Optional<OrderModel> findOrderByClientId(@PathVariable("id")Long id){
        return orderService.findByClientId(id);
    }

    @GetMapping("/findByUser/{username}")
    public List<OrderModel>findOrdersByUserOfClients(@PathVariable("username") String username){
        return this.orderService.findOrdersByUserOfClients(username);
    }

    @GetMapping("/users/{username}")
    public List<ClientModel> findClientsByUsername(@PathVariable("username") String username){
        return this.orderService.findClientsByUsername(username);
    }

    @GetMapping("/maxId")
    public Long findMaxId(){
        return this.orderService.getMaxOrderId();
    }
}
