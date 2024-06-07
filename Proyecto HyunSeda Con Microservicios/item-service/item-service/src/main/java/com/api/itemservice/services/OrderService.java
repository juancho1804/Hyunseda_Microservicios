package com.api.itemservice.services;

import com.api.itemservice.models.ClientModel;
import com.api.itemservice.models.OrderModel;
import com.api.itemservice.repositories.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private RestTemplate restTemplate; //

    private final String CLIENT_SERVICE_URL = "http://localhost:8003/clients";

    public OrderModel crearOrder(OrderModel order) {
        return orderRepository.save(order);
    }

    public OrderModel crearOrderCliente(Long id,OrderModel order) {
        ClientModel clientModel=this.findClientById(id);
        if( clientModel!=null){
            order.setClientModel(clientModel);
            return orderRepository.save(order);
        }
        return null;
    }
    public List<OrderModel> listOrders() {
        return orderRepository.findAll();
    }


    public Long getMaxOrderId() {
        if(orderRepository.count()==0){
            return 1L;
        }
        return orderRepository.findMaxId();
    }
    public ClientModel findClientById(Long id) {
        ResponseEntity<ClientModel> response = restTemplate.getForEntity(CLIENT_SERVICE_URL + "/byId/" + id, ClientModel.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            ClientModel clientModel = response.getBody();
            return clientModel;
        }
        return null;
    }

    @Override
    public List<ClientModel> findClientsByUsername(String username) {
        ResponseEntity<ClientModel[]> response = restTemplate.getForEntity(CLIENT_SERVICE_URL + "/byUsername/" + username, ClientModel[].class);

        if (response.getStatusCode().is2xxSuccessful()) {
            ClientModel[] clientsArray = response.getBody();
            if (clientsArray != null) {
                return Arrays.asList(clientsArray);
            }
        }

        return List.of(); // Devolver una lista vacía si no se encontraron clientes o hubo un error
    }


    @Override
    public Optional<OrderModel> findByClientId(Long id) {
        return orderRepository.findByClientId(id);
    }

    @Override
    public List<OrderModel> findOrdersByUserOfClients(String username) {

        List<ClientModel> clients = this.findClientsByUsername(username);
        List<OrderModel> orders = new ArrayList<>();

        for (ClientModel clientModel : clients) {
            Optional<OrderModel> orderModelOptional = this.findByClientId(clientModel.getId());
            if (orderModelOptional.isPresent()) {
                orders.add(orderModelOptional.get());
            }
        }

        return orders;

    }
}
