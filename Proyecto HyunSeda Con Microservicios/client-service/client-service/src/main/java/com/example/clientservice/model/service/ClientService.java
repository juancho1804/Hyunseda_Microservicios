package com.example.clientservice.model.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.clientservice.model.entity.Client;
import com.example.clientservice.repository.iClientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final iClientRepository clientRepo;

    public void createClient(Client client)
    {
        clientRepo.save(client);
    }

    public ArrayList<Client> getClients()
    {
        return (ArrayList<Client>) clientRepo.findAll();
    }

    public Optional<Client> getClientById(Integer id)
    {
        return clientRepo.findById(id);
    }

    public Client updateClientById(Client request, Integer id)
    {
        Client client = clientRepo.findById(id).get();

        client.setFirstName(request.getFirstName());
        client.setLastName(request.getLastName());
        client.setAddress(request.getAddress());

        return client;
    }

    public Boolean deleteClientById(Integer id)
    {
        try
        {
            clientRepo.deleteById(id);
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
}
