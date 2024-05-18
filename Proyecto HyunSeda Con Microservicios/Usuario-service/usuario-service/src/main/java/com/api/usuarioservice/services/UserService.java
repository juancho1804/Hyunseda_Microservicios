package com.api.usuarioservice.services;

import com.api.usuarioservice.exceptions.EnumErrorCodes;
import com.api.usuarioservice.exceptions.ResourceNotFoundException;
import com.api.usuarioservice.exceptions.UserDomainException;
import com.api.usuarioservice.exceptions.UserError;
import com.api.usuarioservice.models.UserModel;
import com.api.usuarioservice.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public UserModel findByName(String name) {
        ArrayList<UserModel> userList = (ArrayList<UserModel>) this.userRepository.findAll();
        //UserModel userRequest;
        for (UserModel user : userList) {
            if (name.equalsIgnoreCase(user.getName())) {
                return user;
            }
        }
        return null;
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
/*
    public boolean validateUserCredentials(String username, String password) {
        // Buscar el usuario por su nombre de usuario
        UserModel user = this.findByName(username);

        // Verificar si el usuario existe
        if (user != null) {
            if(user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

 */
public ResponseEntity<Boolean> validateUserCredentials(String username, String password) {
    // Buscar el usuario por su nombre de usuario
    UserModel user = this.findByName(username);

    // Verificar si el usuario existe y si la contraseña coincide
    if (user != null && user.getPassword().equals(password)) {
        return ResponseEntity.ok(true); // Credenciales válidas
    } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false); // Credenciales inválidas
    }
}


    private List<UserError> validateDomain(UserModel user) {
        List<UserError> errors = new ArrayList<>();

        if (user.getName() == null || user.getName().isBlank()) {
            errors.add(new UserError(EnumErrorCodes.EMPTY_FIELD, "name", "El nombre del usuario es obligatorio"));
        }

        if (user.getPassword() == null || user.getPassword().isBlank()) {
            errors.add(new UserError(EnumErrorCodes.EMPTY_FIELD, "password", "La contraseña del usuario es obligatoria"));

        }
        if(this.findByName(user.getName())!=null){
            errors.add(new UserError(EnumErrorCodes.EXISTENT_FIELD, "name", "El nombre de usuario ya existe"));

        }
        return errors;

    }
}
