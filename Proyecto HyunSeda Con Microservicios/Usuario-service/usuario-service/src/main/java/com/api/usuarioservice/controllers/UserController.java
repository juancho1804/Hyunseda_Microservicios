package com.api.usuarioservice.controllers;

import com.api.usuarioservice.exceptions.ResourceNotFoundException;
import com.api.usuarioservice.exceptions.UserDomainException;
import com.api.usuarioservice.models.UserModel;
import com.api.usuarioservice.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador REST que maneja las solicitudes relacionadas con los usuarios.
 */
@RestController
@RequestMapping("/UserModel")
public class UserController {
    @Autowired
    IUserService userService;

    @GetMapping
    public List<UserModel> getAllUsers() {
        return userService.getUsers();
    }
    @PostMapping
    public UserModel createUser(@RequestBody UserModel userModel) {
        return userService.saveUser(userModel);
    }
}
