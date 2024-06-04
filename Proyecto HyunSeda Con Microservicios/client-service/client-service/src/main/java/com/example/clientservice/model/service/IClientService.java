package com.example.clientservice.model.service;

import com.example.clientservice.model.entity.Client;
import com.example.clientservice.model.entity.UserModel;

public interface IClientService {
    public UserModel findUser(String token, String username);
    public Client crearCliente(Client client);
}
