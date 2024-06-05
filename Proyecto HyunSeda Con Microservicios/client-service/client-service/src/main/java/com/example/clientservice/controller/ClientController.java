package com.example.clientservice.controller;

import com.example.clientservice.model.entity.Client;

import com.example.clientservice.model.entity.User;
import com.example.clientservice.model.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/{token}/{username}")
    public User findUser(@PathVariable String token, @PathVariable String username) {
        return clientService.findUser(token, username);
    }
    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientService.crearCliente(client);
    }
    @GetMapping("/{username}")
    public Client findClient(@PathVariable String username) {
        return clientService.findByUsername(username);
    }
    @PutMapping("/{username}")
    public Client updateClient(@PathVariable String username,@RequestBody Client client) {
        return clientService.actualizarCliente(username,client);
    }
    @GetMapping("/byId/{id}")
    public Optional<Client>findById(@PathVariable Long id){
        return clientService.findById(id);
    }
}
