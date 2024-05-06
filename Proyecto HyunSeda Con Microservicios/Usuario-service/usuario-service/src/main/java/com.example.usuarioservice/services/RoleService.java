package com.example.usuarioservice.services;

import com.example.usuarioservice.exceptions.EnumErrorCodes;
import com.example.usuarioservice.exceptions.ResourceNotFoundException;
import com.example.usuarioservice.exceptions.UserDomainException;
import com.example.usuarioservice.exceptions.UserError;
import com.example.usuarioservice.models.RoleModel;
import com.example.usuarioservice.repositories.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del servicio de roles que proporciona operaciones relacionadas con los roles de usuario.
 */
@Service
public class RoleService implements IRoleService {

    @Autowired
    IRoleRepository roleRepository;

    /**
     * Obtiene todos los roles.
     * @return Una lista de objetos RoleModel representando todos los roles.
     */
    @Override
    public ArrayList<RoleModel> getRoles() {
        return (ArrayList<RoleModel>) this.roleRepository.findAll();
    }

    /**
     * Guarda un nuevo rol.
     * @param newRoleModel El nuevo rol a ser guardado.
     * @return El rol guardado.
     * @throws UserDomainException Si ocurre un error relacionado con la lógica del dominio del usuario.
     */
    @Override
    public RoleModel save(RoleModel newRoleModel) throws UserDomainException {
        List<UserError> errors = validateDomain(newRoleModel);
        if (!errors.isEmpty()) {
            throw new UserDomainException(errors);
        }
        return roleRepository.save(newRoleModel);
    }

    /**
     * Busca un rol por su identificador único.
     * @param id El identificador del rol a buscar.
     * @return El rol encontrado.
     * @throws ResourceNotFoundException Si el rol no puede ser encontrado.
     */
    @Override
    public RoleModel findById(Long id) throws ResourceNotFoundException {
        RoleModel role = this.roleRepository.findById(id).orElse(null);
        if(role == null){
            throw new ResourceNotFoundException();
        }
        return role;
    }

    /**
     * Busca un rol por su nombre.
     * @param name El nombre del rol a buscar.
     * @return El rol encontrado, o null si no se encuentra.
     */
    @Override
    public RoleModel findByName(String name) {
        RoleModel roleModel = new RoleModel();
        for (RoleModel role : this.roleRepository.findAll()) {
            if (role.getName().equals(name)) {
                return role;
            }
        }
        return null;
    }

    /**
     * Actualiza un rol existente por su identificador único.
     * @param roleModel El rol con los nuevos datos.
     * @param id El identificador del rol a actualizar.
     * @return El rol actualizado.
     * @throws UserDomainException Si ocurre un error relacionado con la lógica del dominio del usuario.
     * @throws ResourceNotFoundException Si el rol no puede ser encontrado.
     */
    @Override
    public RoleModel updateById(RoleModel roleModel, long id) throws UserDomainException, ResourceNotFoundException {
        RoleModel role = this.findById(id);
        if (role == null) {
            throw new ResourceNotFoundException();
        }
        List<UserError> errors = validateDomain(roleModel);
        if (!errors.isEmpty()) {
            throw new UserDomainException(errors);
        }
        role.setName(roleModel.getName());
        roleRepository.save(role);
        return role;
    }

    /**
     * Elimina un rol por su identificador único.
     * @param id El identificador del rol a eliminar.
     * @return true si se eliminó correctamente, false de lo contrario.
     * @throws ResourceNotFoundException Si el rol no puede ser encontrado.
     */
    @Override
    public boolean deleteById(Long id) throws ResourceNotFoundException {
        RoleModel roleModel = this.findById(id);
        if (roleModel == null) {
            throw new ResourceNotFoundException();
        }
        return true;
    }

    /**
     * Valida el modelo de dominio del rol.
     * @param roleModel El modelo del rol a validar.
     * @return Lista de errores encontrados durante la validación.
     */
    private List<UserError> validateDomain(RoleModel roleModel) {
        List<UserError> errors = new ArrayList<>();

        if (roleModel.getName() == null || roleModel.getName().isBlank()) {
            errors.add(new UserError(EnumErrorCodes.EMPTY_FIELD, "name", "El nombre del ROL es obligatorio"));
        }

        return errors;
    }
}
