package com.example.clientservice.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clients")
@IdClass(ClientId.class)
public class Client extends User {
    @Id
    @GeneratedValue
    private Long id;
    @Id
    String username;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String address;
}
