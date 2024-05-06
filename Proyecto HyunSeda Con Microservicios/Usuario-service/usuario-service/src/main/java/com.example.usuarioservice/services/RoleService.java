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

@Service
public class RoleService implements IRoleService {
    @Autowired
    IRoleRepository roleRepository;


    @Override
    public ArrayList<RoleModel> getRoles() {
        return (ArrayList<RoleModel>) this.roleRepository.findAll();

    }

    @Override
    public RoleModel save(RoleModel newRoleModel) throws UserDomainException {
        List<UserError> errors = validateDomain(newRoleModel);
        if (!errors.isEmpty()) {
            throw new UserDomainException(errors);
        }
        return roleRepository.save(newRoleModel);
    }

    @Override
    public RoleModel findById(Long id) throws ResourceNotFoundException {
        RoleModel role = this.roleRepository.findById(id).orElse(null);
        if(role == null){
            throw new ResourceNotFoundException();
        }
        return role;
    }

    @Override
    public RoleModel findByName(String name) {
        RoleModel roleModel=new RoleModel();
        for(RoleModel role : this.roleRepository.findAll()) {
            if(role.getName().equals(name)) {
                return role;
            }
        }
        return null;
    }

    @Override
    public RoleModel updateById(RoleModel roleModel, long id) throws UserDomainException, ResourceNotFoundException {
        RoleModel role = this.findById(id);
        if(role == null){
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

    @Override
    public boolean deleteById(Long id) throws ResourceNotFoundException {
        RoleModel roleModel=this.findById(id);
        if(roleModel==null){
            throw new ResourceNotFoundException();
        }
        return true;
    }
    private List<UserError> validateDomain(RoleModel roleModel) {
        List<UserError> errors = new ArrayList<>();

        if (roleModel.getName() == null || roleModel.getName().isBlank()) {
            errors.add(new UserError(EnumErrorCodes.EMPTY_FIELD, "name", "El nombre del ROL es obligatorio"));
        }

        return errors;

    }
}
