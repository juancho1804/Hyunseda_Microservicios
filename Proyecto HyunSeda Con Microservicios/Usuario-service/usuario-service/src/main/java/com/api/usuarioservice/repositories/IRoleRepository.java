package com.api.usuarioservice.repositories;

import com.api.usuarioservice.models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<RoleModel, Long> {
}
