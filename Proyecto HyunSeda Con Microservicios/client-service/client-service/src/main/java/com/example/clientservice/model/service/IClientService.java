package com.example.clientservice.model.service;

import com.example.clientservice.model.entity.Client;
import com.example.clientservice.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface IClientService {

    /**
     * @brief Método para buscar un usuario por su token y nombre de usuario.
     *
     * @param token El token del usuario.
     * @param username El nombre de usuario del usuario.
     * @return El usuario si la solicitud fue exitosa, o null en caso contrario.
     */
    User findUser(String token, String username);

    /**
     * @brief Método para crear un nuevo cliente.
     *
     * @param client El cliente a crear.
     * @return El cliente creado.
     */
    Client crearCliente(Client client);

    /**
     * @brief Método para buscar un cliente por su ID.
     *
     * @param id El ID del cliente.
     * @return Un Optional que puede contener el cliente si se encuentra.
     */
    Optional<Client> findById(Long id);

    /**
     * @brief Método para buscar clientes por su nombre de usuario.
     *
     * @param username El nombre de usuario del cliente.
     * @return Una lista de clientes con el nombre de usuario especificado.
     */
    List<Client> findClientsByUsername(String username);
}
