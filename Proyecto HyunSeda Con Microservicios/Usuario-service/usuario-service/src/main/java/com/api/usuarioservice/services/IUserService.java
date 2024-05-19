package com.api.usuarioservice.services;

import com.api.usuarioservice.demojwt.Auth.AuthResponse;
import com.api.usuarioservice.demojwt.Auth.LoginRequest;
import com.api.usuarioservice.demojwt.Auth.RegisterRequest;
import com.api.usuarioservice.exceptions.ResourceNotFoundException;
import com.api.usuarioservice.exceptions.UserDomainException;
import com.api.usuarioservice.models.UserModel;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Interfaz que define las operaciones disponibles para el servicio de usuarios.
 */
public interface IUserService {
    public List<UserModel> getUsers();
    public UserModel saveUser(UserModel user);
}
