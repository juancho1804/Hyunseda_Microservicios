package com.example.clientservice.model.service;

import com.example.clientservice.model.entity.Client;
import com.example.clientservice.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface IClientService {
    User findUser(String token, String username);
    Client crearCliente(Client client);
    Optional<Client> findById(Long id);
    List<Client> findClientsByUsername(String username);
}
