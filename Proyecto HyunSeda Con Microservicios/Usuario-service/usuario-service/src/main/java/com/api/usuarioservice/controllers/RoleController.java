package com.api.usuarioservice.controllers;

import com.api.usuarioservice.exceptions.ResourceNotFoundException;
import com.api.usuarioservice.exceptions.UserDomainException;
import com.api.usuarioservice.models.RoleModel;
import com.api.usuarioservice.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Controlador REST que maneja las solicitudes relacionadas con los roles de usuario.
 */
@RestController
@RequestMapping("/RoleModel")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    /**
     * Obtiene todos los roles disponibles.
     * @return Una lista de objetos RoleModel representando todos los roles disponibles.
     */
    @GetMapping
    public ArrayList<RoleModel> getRoles() {
        return roleService.getRoles();
    }

    /**
     * Guarda un nuevo rol.
     * @param newRoleModel El nuevo rol a ser guardado.
     * @return El rol guardado.
     * @throws UserDomainException Si ocurre un error relacionado con la lógica del dominio del usuario.
     */
    @PostMapping
    public RoleModel save(@RequestBody RoleModel newRoleModel) throws UserDomainException {
        return roleService.save(newRoleModel);
    }

    /**
     * Busca un rol por su identificador único.
     * @param id El identificador del rol a buscar.
     * @return El rol encontrado.
     * @throws ResourceNotFoundException Si el rol no puede ser encontrado.
     */
    @GetMapping(path = "/byId/{id}")
    public RoleModel findById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return roleService.findById(id);
    }

    /**
     * Busca un rol por su nombre.
     * @param name El nombre del rol a buscar.
     * @return El rol encontrado.
     */
    @GetMapping(path = "/byName/{name}")
    public RoleModel findByName(@PathVariable("name") String name) {
        return roleService.findByName(name);
    }

    /**
     * Actualiza un rol existente por su identificador único.
     * @param roleModel El rol con los nuevos datos.
     * @param id El identificador del rol a actualizar.
     * @return El rol actualizado.
     * @throws UserDomainException Si ocurre un error relacionado con la lógica del dominio del usuario.
     * @throws ResourceNotFoundException Si el rol no puede ser encontrado.
     */
    @PutMapping(path = "{id}")
    public RoleModel updateById(@RequestBody RoleModel roleModel, @PathVariable("id") long id) throws UserDomainException, ResourceNotFoundException {
        return roleService.updateById(roleModel, id);
    }

    /**
     * Elimina un rol por su identificador único.
     * @param id El identificador del rol a eliminar.
     * @return true si se eliminó correctamente, false de lo contrario.
     * @throws ResourceNotFoundException Si el rol no puede ser encontrado.
     */
    @DeleteMapping(path = "{id}")
    public boolean deleteById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        if (roleService.deleteById(id)) {
            System.out.println("Role deleted successfully");
            return true;
        }
        System.out.println("Role not deleted successfully");
        return false;
    }
}
