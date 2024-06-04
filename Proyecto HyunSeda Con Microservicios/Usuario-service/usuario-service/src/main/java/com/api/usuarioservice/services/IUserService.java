package com.api.usuarioservice.services;

import com.api.usuarioservice.models.UserModel;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define las operaciones disponibles para el servicio de usuarios.
 */
public interface IUserService {
    public List<UserModel> getUsers();
    public UserModel saveUser(UserModel user);
    public Optional<UserModel> findByUsername(String username);
}
