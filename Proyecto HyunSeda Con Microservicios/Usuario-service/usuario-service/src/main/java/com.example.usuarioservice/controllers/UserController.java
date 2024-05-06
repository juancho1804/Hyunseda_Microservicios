package com.example.usuarioservice.controllers;

import com.example.usuarioservice.exceptions.ResourceNotFoundException;
import com.example.usuarioservice.exceptions.UserDomainException;
import com.example.usuarioservice.models.UserModel;
import com.example.usuarioservice.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Controlador REST que maneja las solicitudes relacionadas con los usuarios.
 */
@RestController
@RequestMapping("/UserModel")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * Obtiene todos los usuarios.
     * @return Una lista de objetos UserModel representando todos los usuarios.
     */
    @GetMapping
    public ArrayList<UserModel> getAllUsers() {
        return this.userService.getUsers();
    }

    /**
     * Crea un nuevo usuario.
     * @param user El nuevo usuario a ser creado.
     * @return El usuario creado.
     * @throws UserDomainException Si ocurre un error relacionado con la lógica del dominio del usuario.
     */
    @PostMapping
    public UserModel createUser(@RequestBody UserModel user) throws UserDomainException {
        return this.userService.save(user);
    }

    /**
     * Obtiene un usuario por su identificador único.
     * @param id El identificador del usuario a buscar.
     * @return El usuario encontrado.
     * @throws UserDomainException Si ocurre un error relacionado con la lógica del dominio del usuario.
     * @throws ResourceNotFoundException Si el usuario no puede ser encontrado.
     */
    @GetMapping(path="/byId/{id}")
    public UserModel getUserById(@PathVariable("id") Long id) throws UserDomainException, ResourceNotFoundException {
        return this.userService.findById(id);
    }

    /**
     * Obtiene un usuario por su nombre.
     * @param name El nombre del usuario a buscar.
     * @return El usuario encontrado.
     */
    @GetMapping(path = "/byName/{name}")
    public UserModel getUserByName(@PathVariable("name") String name) {
        return this.userService.findByName(name);
    }

    /**
     * Actualiza un usuario existente por su identificador único.
     * @param user El usuario con los nuevos datos.
     * @param id El identificador del usuario a actualizar.
     * @return El usuario actualizado.
     * @throws UserDomainException Si ocurre un error relacionado con la lógica del dominio del usuario.
     * @throws ResourceNotFoundException Si el usuario no puede ser encontrado.
     */
    @PutMapping(path = "/{id}")
    public UserModel updateById(@RequestBody UserModel user, @PathVariable("id") long id) throws UserDomainException, ResourceNotFoundException {
        return this.userService.updateById(user, id);
    }

    /**
     * Elimina un usuario por su identificador único.
     * @param id El identificador del usuario a eliminar.
     * @return true si se eliminó correctamente, false de lo contrario.
     */
    @DeleteMapping(path = "{id}")
    public boolean deleteById(@PathVariable("id") long id) {
        boolean ok = this.userService.deleteById(id);
        if (ok) {
            System.out.println("User deleted successfully");
            return true;
        }
        System.out.println("User not deleted successfully");
        return false;
    }

    /**
     * Valida las credenciales de usuario.
     * @param username El nombre de usuario.
     * @param password La contraseña de usuario.
     * @return ResponseEntity con un booleano que indica si las credenciales son válidas o no.
     */
    @GetMapping(path="/validate/{username}/{password}")
    public ResponseEntity<Boolean> validarUsuario(@PathVariable("username")String username, @PathVariable("password")String password){
        return this.userService.validateUserCredentials(username, password);
    }
}
