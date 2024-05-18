package com.api.usuarioservice.services;

import com.api.usuarioservice.exceptions.ResourceNotFoundException;
import com.api.usuarioservice.exceptions.UserDomainException;
import com.api.usuarioservice.models.UserModel;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

/**
 * Interfaz que define las operaciones disponibles para el servicio de usuarios.
 */
public interface IUserService {

    /**
     * Obtiene todos los usuarios.
     * @return Una lista de objetos UserModel representando todos los usuarios.
     */
    public ArrayList<UserModel> getUsers();

    /**
     * Guarda un nuevo usuario.
     * @param newUser El nuevo usuario a ser guardado.
     * @return El usuario guardado.
     * @throws UserDomainException Si ocurre un error relacionado con la lógica del dominio del usuario.
     */
    public UserModel save(UserModel newUser) throws UserDomainException;

    /**
     * Busca un usuario por su identificador único.
     * @param id El identificador del usuario a buscar.
     * @return El usuario encontrado.
     * @throws ResourceNotFoundException Si el usuario no puede ser encontrado.
     */
    public UserModel findById(Long id) throws ResourceNotFoundException;

    /**
     * Busca un usuario por su nombre.
     * @param name El nombre del usuario a buscar.
     * @return El usuario encontrado, o null si no se encuentra.
     */
    public UserModel findByName(String name);

    /**
     * Actualiza un usuario existente por su identificador único.
     * @param newUser El usuario con los nuevos datos.
     * @param id El identificador del usuario a actualizar.
     * @return El usuario actualizado.
     * @throws UserDomainException Si ocurre un error relacionado con la lógica del dominio del usuario.
     * @throws ResourceNotFoundException Si el usuario no puede ser encontrado.
     */
    public UserModel updateById(UserModel newUser, long id) throws UserDomainException, ResourceNotFoundException;

    /**
     * Elimina un usuario por su identificador único.
     * @param id El identificador del usuario a eliminar.
     * @return true si se eliminó correctamente, false de lo contrario.
     */
    public boolean deleteById(Long id);

    /**
     * Valida las credenciales de usuario.
     * @param username El nombre de usuario.
     * @param password La contraseña de usuario.
     * @return ResponseEntity con un booleano que indica si las credenciales son válidas o no.
     */
    public ResponseEntity<Boolean> validateUserCredentials(String username, String password);
}
