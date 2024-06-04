package com.api.itemservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Clientes")
public class ClientModel {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String address;
}
