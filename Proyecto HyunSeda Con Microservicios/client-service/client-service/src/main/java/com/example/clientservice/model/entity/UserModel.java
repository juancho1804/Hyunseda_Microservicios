package com.example.clientservice.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class UserModel {
    @Id
    private String username;
    @Column
    private String email;
    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    private RoleModel roleModel;
}
