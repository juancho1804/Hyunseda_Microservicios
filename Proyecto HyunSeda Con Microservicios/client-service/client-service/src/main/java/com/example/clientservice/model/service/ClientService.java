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


    /**
     * @brief Método para buscar un usuario por su token y nombre de usuario.
     *
     * Este método realiza una solicitud HTTP GET al servicio de usuarios para buscar un usuario por su token y nombre de usuario.
     *
     * @param token El token del usuario.
     * @param username El nombre de usuario del usuario.
     * @return El usuario si la solicitud fue exitosa, o null en caso contrario.
     */
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

    /**
     * @brief Método para crear un nuevo cliente.
     *
     * Este método utiliza el repositorio de clientes para guardar un nuevo cliente en la base de datos.
     *
     * @param client El cliente a crear.
     * @return El cliente creado.
     */
    @Override
    public Client crearCliente(Client client) {
        return clientRepo.save(client);
    }

    /**
     * @brief Método para buscar un cliente por su ID.
     *
     * Este método utiliza el repositorio de clientes para buscar un cliente en la base de datos por su ID.
     *
     * @param id El ID del cliente.
     * @return Un Optional que puede contener el cliente si se encuentra.
     */
    @Override
    public Optional<Client> findById(Long id){
        return clientRepo.findById(id);
    }

    /**
     * @brief Método para buscar clientes por su nombre de usuario.
     *
     * Este método utiliza el repositorio de clientes para buscar clientes en la base de datos por su nombre de usuario.
     *
     * @param username El nombre de usuario del cliente.
     * @return Una lista de clientes con el nombre de usuario especificado.
     */
    @Override
    public List<Client> findClientsByUsername(String username) {
        return clientRepo.findClientsByUsername(username);
    }



}
