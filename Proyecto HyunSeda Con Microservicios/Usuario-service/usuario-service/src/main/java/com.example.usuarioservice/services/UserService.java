package com.example.usuarioservice.services;

import com.example.usuarioservice.exceptions.EnumErrorCodes;
import com.example.usuarioservice.exceptions.ResourceNotFoundException;
import com.example.usuarioservice.exceptions.UserDomainException;
import com.example.usuarioservice.exceptions.UserError;
import com.example.usuarioservice.models.UserModel;
import com.example.usuarioservice.repositories.IUserRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService{

    @Autowired
    IUserRepository userRepository;

    @Override
    public ArrayList<UserModel> getUsers() {
        return (ArrayList<UserModel>) userRepository.findAll();
    }

    @Override
    public UserModel save(UserModel newUser) throws UserDomainException {
        List<UserError> errors = validateDomain(newUser);
        if (!errors.isEmpty()) {
            throw new UserDomainException(errors);
        }
        return userRepository.save(newUser);
    }

    @Override
    public UserModel findById(Long id) throws ResourceNotFoundException {
        UserModel user = this.userRepository.findById(id).orElse(null);
        if(user == null){
            throw new ResourceNotFoundException();
        }

        return user;
    }

    @Override
    public ArrayList<UserModel> findByName(String name) {
        ArrayList<UserModel> userList = (ArrayList<UserModel>) this.userRepository.findAll();
        ArrayList<UserModel> usersWithNamel = new ArrayList<>();
        for (UserModel user : userList) {
            if (name.equalsIgnoreCase(user.getName())) {
                usersWithNamel.add(user);
            }
        }
        return usersWithNamel;
    }

    @Override
    public UserModel updateById(UserModel newUser, long id) throws UserDomainException, ResourceNotFoundException {
        UserModel user= this.findById(id);
        if(user == null){
            throw new ResourceNotFoundException();
        }
        List<UserError> errors = validateDomain(user);

        if (!errors.isEmpty()) {
            throw new UserDomainException(errors);
        }

        user.setId(newUser.getId());
        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());
        user.setRoleModel(newUser.getRoleModel());

        userRepository.save(user);
        return user;
    }

    @Override
    public boolean deleteById(Long id) {
        try{
            this.userRepository.deleteById(id);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    private List<UserError> validateDomain(UserModel user) {
        List<UserError> errors = new ArrayList<>();

        if (user.getName() == null || user.getName().isBlank()) {
            errors.add(new UserError(EnumErrorCodes.EMPTY_FIELD, "name", "El nombre del usuario es obligatorio"));
        }

        if (user.getPassword() == null || user.getPassword().isBlank()) {
            errors.add(new UserError(EnumErrorCodes.EMPTY_FIELD, "password", "La contraseña del usuario es obligatoria"));

        }
        return errors;

    }
}
