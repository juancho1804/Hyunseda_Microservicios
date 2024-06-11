package com.example.clientservice.controller;

import com.example.clientservice.model.entity.Client;

import com.example.clientservice.model.entity.User;
import com.example.clientservice.model.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "Encontrar usuario por token y nombre de usuario",
            description = "Obtiene un usuario proporcionando un token y un nombre de usuario.")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado con éxito",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class),
                    examples = @ExampleObject(value = "{\"userId\": 1, \"username\": \"jojo\", \"token\": \"abcd1234\"}")))
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{\"errors\": [{\"code\": \"NOT_FOUND\", \"message\": \"Usuario no encontrado\"}]}")))
    public User findUser(@PathVariable String token, @PathVariable String username) {
        return clientService.findUser(token, username);
    }

    @PostMapping
    @Operation(summary = "Crear un cliente",
            description = "Crea un nuevo cliente proporcionando los datos requeridos.")
    @ApiResponse(responseCode = "200", description = "Cliente creado con éxito",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class),
                    examples = @ExampleObject(value = "{\"clientId\": 1, \"name\": \"Jose Jose\", \"email\": \"jojo@example.com\"}")))
    @ApiResponse(responseCode = "422", description = "Datos de cliente inválidos",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{\"errors\": [{\"code\": \"INVALID_DATA\", \"message\": \"Los datos del cliente son inválidos\"}]}")))
    public Client createClient(@RequestBody Client client) {
        return clientService.crearCliente(client);
    }

    @GetMapping("/byId/{id}")
    @Operation(summary = "Encontrar cliente por ID",
            description = "Obtiene un cliente proporcionando su ID.")
    @ApiResponse(responseCode = "200", description = "Cliente encontrado con éxito",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class),
                    examples = @ExampleObject(value = "{\"clientId\": 1, \"name\": \"Jose Jose\", \"email\": \"jojo@example.com\"}")))
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{\"errors\": [{\"code\": \"NOT_FOUND\", \"message\": \"Cliente no encontrado\"}]}")))
    public Optional<Client>findById(@PathVariable Long id){
        return clientService.findById(id);
    }

    @GetMapping("/byUsername/{username}")
    @Operation(summary = "Encontrar clientes por nombre de usuario",
            description = "Obtiene una lista de clientes proporcionando el nombre de usuario.")
    @ApiResponse(responseCode = "200", description = "Clientes encontrados con éxito",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class),
                    examples = @ExampleObject(value = "[{\"clientId\": 1, \"name\": \"Jose Jose\", \"username\": \"jojo\"}]")))
    @ApiResponse(responseCode = "404", description = "Clientes no encontrados",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{\"errors\": [{\"code\": \"NOT_FOUND\", \"message\": \"Clientes no encontrados\"}]}")))
    public List<Client>findClientsByUsername(@PathVariable String username){
        return clientService.findClientsByUsername(username);
    }
}
