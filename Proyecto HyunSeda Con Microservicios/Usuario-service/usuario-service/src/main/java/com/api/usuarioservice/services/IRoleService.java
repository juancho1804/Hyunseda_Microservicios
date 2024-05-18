package com.api.usuarioservice.services;

import com.api.usuarioservice.exceptions.ResourceNotFoundException;
import com.api.usuarioservice.exceptions.UserDomainException;
import org.springframework.stereotype.Service;

import com.api.usuarioservice.models.RoleModel;

import java.util.ArrayList;

/**
 * Interfaz que define las operaciones disponibles para el servicio de roles de usuario.
 */
@Service
public interface IRoleService {

    /**
     * Obtiene todos los roles disponibles.
     * @return Una lista de objetos RoleModel representando todos los roles disponibles.
     */
    public ArrayList<RoleModel> getRoles();

    /**
     * Guarda un nuevo rol.
     * @param newRoleModel El nuevo rol a ser guardado.
     * @return El rol guardado.
     * @throws UserDomainException Si ocurre un error relacionado con la lógica del dominio del usuario.
     */
    public RoleModel save(RoleModel newRoleModel) throws UserDomainException;

    /**
     * Busca un rol por su identificador único.
     * @param id El identificador del rol a buscar.
     * @return El rol encontrado.
     * @throws ResourceNotFoundException Si el rol no puede ser encontrado.
     */
    public RoleModel findById(Long id) throws ResourceNotFoundException;

    /**
     * Busca un rol por su nombre.
     * @param name El nombre del rol a buscar.
     * @return El rol encontrado, o null si no se encuentra.
     */
    public RoleModel findByName(String name);

    /**
     * Actualiza un rol existente por su identificador único.
     * @param roleModel El rol con los nuevos datos.
     * @param id El identificador del rol a actualizar.
     * @return El rol actualizado.
     * @throws UserDomainException Si ocurre un error relacionado con la lógica del dominio del usuario.
     * @throws ResourceNotFoundException Si el rol no puede ser encontrado.
     */
    public RoleModel updateById(RoleModel roleModel, long id) throws UserDomainException, ResourceNotFoundException;

    /**
     * Elimina un rol por su identificador único.
     * @param id El identificador del rol a eliminar.
     * @return true si se eliminó correctamente, false de lo contrario.
     * @throws ResourceNotFoundException Si el rol no puede ser encontrado.
     */
    public boolean deleteById(Long id) throws ResourceNotFoundException;

}

