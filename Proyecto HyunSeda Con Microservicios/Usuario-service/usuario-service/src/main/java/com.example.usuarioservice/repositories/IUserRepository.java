package com.example.usuarioservice.repositories;

import com.example.usuarioservice.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserModel, Long> {
}
