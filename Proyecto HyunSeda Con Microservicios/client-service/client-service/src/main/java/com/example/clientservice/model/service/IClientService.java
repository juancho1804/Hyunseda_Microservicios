package com.example.clientservice.model.service;

import com.example.clientservice.model.entity.Client;
import com.example.clientservice.model.entity.User;

public interface IClientService {
    public User findUser(String token, String username);
    public Client crearCliente(Client client);
    public Client findByUsername(String username);
    public Client actualizarCliente(String username,Client client);
}
