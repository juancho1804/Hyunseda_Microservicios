package com.api.itemservice.controllers;

import com.api.itemservice.models.OrderModel;
import com.api.itemservice.services.IOrderService;
import com.api.itemservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
}
