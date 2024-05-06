package com.example.usuarioservice.models;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name="Users")

public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String password;

    @ManyToOne
    @JoinColumn(name="role_id")
    private RoleModel roleModel;

    public UserModel(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
