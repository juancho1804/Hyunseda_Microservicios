package com.example.clientservice.model.service;

import com.example.clientservice.model.entity.Client;
import com.example.clientservice.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface IClientService {
    public User findUser(String token, String username);
    public Client crearCliente(Client client);
    public Client findByUsername(String username);
    public Client actualizarCliente(String username,Client client);
    public Optional<Client> findById(Long id);

    public List<Client> findClientsByUsername(String username);
}
