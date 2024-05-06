package com.example.usuarioservice.repositories;

import com.example.usuarioservice.models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<RoleModel, Long> {
}
