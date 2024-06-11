package com.api.usuarioservice.controllers;

import com.api.usuarioservice.models.UserModel;
import com.api.usuarioservice.services.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/**
 * Controlador REST que maneja las solicitudes relacionadas con los usuarios.
 */
@RestController
@RequestMapping("/UserModel")
public class UserController {
    @Autowired
    IUserService userService;

    @GetMapping
    @Operation(summary = "Obtener todos los usuarios",
            description = "Obtiene una lista de todos los usuarios almacenados en la base de datos.")
    @ApiResponse(responseCode = "200", description = "Lista de usuarios recuperada exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserModel.class),
                    examples = @ExampleObject(value = "[{\"id\": 1, \"username\": \"jojo\"}, {\"id\": 2, \"username\": \"dio\"}]")))
    public List<UserModel> getAllUsers() {
        return userService.getUsers();
    }


    @PostMapping
    @Operation(summary = "Crear un usuario",
            description = "Crea un nuevo usuario en la base de datos.")
    @ApiResponse(responseCode = "200", description = "Usuario creado con éxito",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserModel.class),
                    examples = @ExampleObject(value = "{\"username\": \"jojo\", \"password\": \"password123\"}")))
    @ApiResponse(responseCode = "422", description = "El nombre de usuario es obligatorio",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{\"errors\": [{\"code\": \"EMPTY_FIELD\", \"field\": \"username\", \"message\": \"El nombre de usuario es obligatorio\"}]}")))
    public UserModel createUser(@RequestBody UserModel userModel) {

        return userService.saveUser(userModel);
    }


    @GetMapping("/byUsername/{username}")
    @Operation(summary = "Obtener un usuario por nombre de usuario",
            description = "Obtiene un usuario cuyo nombre de usuario coincida exactamente con el nombre ingresado.")
    @ApiResponse(responseCode = "200", description = "Usuario recuperado exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserModel.class),
                    examples = @ExampleObject(value = "{\"id\": 1, \"username\": \"jojo\"}")))
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{\"errors\": [{\"code\": \"NOT_FOUND\"}]}")))
    public Optional<UserModel> getUserByUsername(@PathVariable("username") String username) {
        return userService.findByUsername(username);
    }
}
