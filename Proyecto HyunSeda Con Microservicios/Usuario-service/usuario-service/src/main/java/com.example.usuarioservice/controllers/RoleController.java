package com.example.usuarioservice.controllers;

import com.example.usuarioservice.exceptions.ResourceNotFoundException;
import com.example.usuarioservice.exceptions.UserDomainException;
import com.example.usuarioservice.models.RoleModel;
import com.example.usuarioservice.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/RoleModel")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @GetMapping
    public ArrayList<RoleModel> getRoles() {
        return roleService.getRoles();
    }
    @PostMapping
    public RoleModel save(@RequestBody RoleModel newRoleModel)throws UserDomainException {
        return roleService.save(newRoleModel);
    }

    @GetMapping(path = "/byId/{id}")
    public RoleModel findById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return roleService.findById(id);
    }

    @GetMapping(path = "/byName/{name}")
    public  RoleModel findByName(@PathVariable("name") String name) {
        return roleService.findByName(name);
    }

    @PutMapping(path = "{id}")
    public RoleModel updateById(@RequestBody RoleModel roleModel, @PathVariable("id") long id) throws UserDomainException,ResourceNotFoundException{
        return roleService.updateById(roleModel,id);
    }

    @DeleteMapping(path = "{id}")
    public boolean deleteById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        if(roleService.deleteById(id)) {
            System.out.println("Role deleted successfully");
            return true;
        }
        System.out.println("Role not deleted successfully");
        return false;
    }
}
