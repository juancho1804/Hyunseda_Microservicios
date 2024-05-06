package com.example.usuarioservice.services;


import com.example.usuarioservice.exceptions.ResourceNotFoundException;
import com.example.usuarioservice.exceptions.UserDomainException;
import com.example.usuarioservice.models.UserModel;

import java.util.ArrayList;

public interface IUserService {
    public ArrayList<UserModel> getUsers();
    public UserModel save(UserModel newUser)throws UserDomainException;
    public UserModel findById(Long id)throws ResourceNotFoundException;
    public ArrayList<UserModel>findByName(String name);
    public UserModel updateById(UserModel newUser, long id)throws UserDomainException,ResourceNotFoundException;
    public boolean deleteById(Long id);
}
