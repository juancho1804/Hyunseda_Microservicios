package com.api.itemservice.controllers;

import com.api.itemservice.models.OrderModel;
import com.api.itemservice.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PostMapping(path = "/{id}")
    public OrderModel createOrderClient(@PathVariable("id") Long id, @RequestBody OrderModel order){
        return this.orderService.createOrderClient(id, order);
    }

    @GetMapping
    public List<OrderModel> getAllOrders() {
        return  orderService.getAllOrders();
    }

    @GetMapping("/findByUser/{username}")
    public List<OrderModel>findOrdersByUserOfClients(@PathVariable("username") String username){
        return this.orderService.findOrdersByUserOfClients(username);
    }

    @GetMapping("/maxId")
    public Long findMaxId(){
        return this.orderService.findMaxId();
    }
}
