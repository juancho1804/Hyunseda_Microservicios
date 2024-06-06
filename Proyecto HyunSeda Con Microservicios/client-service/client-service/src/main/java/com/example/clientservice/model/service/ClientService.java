package com.example.clientservice.model.service;

import com.example.clientservice.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import com.example.clientservice.model.entity.Client;
import com.example.clientservice.repository.iClientRepository;

import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements IClientService{
    @Autowired
    private iClientRepository clientRepo;

    @Autowired
    private RestTemplate restTemplate;

    // URL del servicio de productos
    private final String USER_SERVICE_URL = "http://localhost:8004/UserModel";


    @Override
    public User findUser(String token, String username) {
        // Configurar cabecera con el token
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Configurar la solicitud HTTP
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Realizar la solicitud HTTP GET al servicio de usuarios
        ResponseEntity<User> response = restTemplate.exchange(USER_SERVICE_URL + "/byUsername/" + username, HttpMethod.GET, entity, User.class);

        // Verificar si la solicitud fue exitosa
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            // Manejar el caso de error de acuerdo a tus requerimientos
            return null;
        }
    }

    @Override
    public Client crearCliente(Client client) {
        Client client1=findClient(client);
        if(client1 == null){
            System.out.println("El cliente se guardo");
            return clientRepo.save(client);
        }
        System.out.println("El cliente ya existe, no se guardo");
        return client1;
    }


    public Client findClient(Client client) {
        Optional<Client> optionalClient = clientRepo.findByUsernameAndFirstNameAndLastNameAndAddress(
                client.getUsername(), client.getFirstName(), client.getLastName(), client.getAddress()
        );
        return optionalClient.orElse(null);
    }

    public Optional<Client> findById(Long id){
        return clientRepo.findById(id);
    }

    @Override
    public List<Client> findClientsByUsername(String username) {
        return clientRepo.findClientsByUsername(username);
    }

    @Override
    public Client findByUsername(String username) {
        List<Client> clients = clientRepo.findAll();
        for (Client client : clients) {
            if (client.getUsername().equals(username)) {
                return client;
            }
        }
        return null;
    }

    @Override
    public Client actualizarCliente(String username,Client client) {
        Client cli=findByUsername(username);
        cli.setFirstName(client.getFirstName());
        cli.setLastName(client.getLastName());
        cli.setAddress(client.getAddress());

        return clientRepo.save(cli);
    }
}
