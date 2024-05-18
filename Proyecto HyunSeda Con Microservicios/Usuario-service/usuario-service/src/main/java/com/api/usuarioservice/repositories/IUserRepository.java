package com.api.usuarioservice.repositories;

import com.api.usuarioservice.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserModel, Long> {
}
