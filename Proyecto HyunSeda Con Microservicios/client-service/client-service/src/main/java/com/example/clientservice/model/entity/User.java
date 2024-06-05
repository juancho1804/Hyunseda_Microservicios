package com.example.clientservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@MappedSuperclass
public class User {
    @Id
    private String username;
    @Column
    private String email;
    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    private RoleModel roleModel;
}
