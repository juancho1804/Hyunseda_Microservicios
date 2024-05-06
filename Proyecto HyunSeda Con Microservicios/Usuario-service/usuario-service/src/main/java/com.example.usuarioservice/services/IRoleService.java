package com.example.usuarioservice.services;
import org.springframework.stereotype.Service;
import com.example.usuarioservice.exceptions.ResourceNotFoundException;
import com.example.usuarioservice.exceptions.UserDomainException;
import com.example.usuarioservice.models.RoleModel;

import java.util.ArrayList;
@Service
public interface IRoleService {
    public ArrayList<RoleModel> getRoles();
    public RoleModel save(RoleModel newRoleModel)throws UserDomainException;
    public RoleModel findById(Long id)throws ResourceNotFoundException;
    public RoleModel findByName(String name);
    public RoleModel updateById(RoleModel roleModel, long id)throws UserDomainException,ResourceNotFoundException;
    public boolean deleteById(Long id) throws ResourceNotFoundException;

}
