package com.example.clientservice.controller;

import com.example.clientservice.model.entity.Client;

import com.example.clientservice.model.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;



@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {
    private  final ClientService clientService;

    @PostMapping
    public void createClient(@RequestBody Client client)
    {
        clientService.createClient(client);
    }

    @GetMapping
    public ArrayList<Client> getClients()
    {
           return this.clientService.getClients();
    }

    @GetMapping(path = "/{id}")
    public Optional<Client> getClientById(@PathVariable("id") Integer id)
    {
        return this.clientService.getClientById(id);
    }

    @PutMapping(path = "/{id}")
    public Client updateClientById(@RequestBody Client request,@PathVariable("id") Integer id)
    {
        return this.clientService.updateClientById(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteClientById(@PathVariable("id") Integer id)
    {
        boolean ok = this.clientService.deleteClientById(id);

        if(ok)
        {
            return "Se ha eliminado a la persona con id: " + id;
        }
        else
        {
            return "Error, tenemos un problema intentando eliminar a la persona con id: " + id;
        }
    }

}
