package com.api.itemservice.services;

import com.api.itemservice.models.ClientModel;
import com.api.itemservice.models.OrderModel;
import com.api.itemservice.models.ProductModel;
import com.api.itemservice.repositories.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private RestTemplate restTemplate; //

    private final String CLIENT_SERVICE_URL = "http://localhost:8003/client";

    public OrderModel crearOrder(OrderModel order) {
        return orderRepository.save(order);
    }

    public OrderModel crearOrderCliente(Integer id,OrderModel order) {
        ClientModel clientModel=this.findClientById(id);
        if( clientModel!=null){
            order.setClient(clientModel);
            return orderRepository.save(order);
        }
        return null;
    }
    public List<OrderModel> listOrders() {
        return orderRepository.findAll();
    }

    public ClientModel findClientById(Integer id) {
        ResponseEntity<ClientModel> response = restTemplate.getForEntity(CLIENT_SERVICE_URL + "/" + id, ClientModel.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            ClientModel clientModel = response.getBody();
            return clientModel;
        }
        return null;
    }
}
