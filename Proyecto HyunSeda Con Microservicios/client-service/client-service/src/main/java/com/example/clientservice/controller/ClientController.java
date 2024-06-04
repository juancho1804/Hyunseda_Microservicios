package com.example.clientservice.controller;

import com.example.clientservice.model.entity.Client;

import com.example.clientservice.model.entity.UserModel;
import com.example.clientservice.model.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;



@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/{token}/{username}")
    public UserModel findUser(@PathVariable String token, @PathVariable String username) {
        return clientService.findUser(token, username);
    }
    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientService.crearCliente(client);
    }



}
