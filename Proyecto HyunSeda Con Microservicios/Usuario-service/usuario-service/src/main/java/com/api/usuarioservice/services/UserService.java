package com.api.usuarioservice.services;

import com.api.usuarioservice.models.UserModel;
import com.api.usuarioservice.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<UserModel> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserModel saveUser(UserModel user) {
        return userRepository.save(user);
    }
}
