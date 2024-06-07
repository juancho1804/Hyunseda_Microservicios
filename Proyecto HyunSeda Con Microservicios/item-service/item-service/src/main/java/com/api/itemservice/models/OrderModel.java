package com.api.itemservice.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "OrdersPRUEBA1")
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String date;
    @Column
    private String items;

    @OneToOne(cascade = CascadeType.ALL)
    private ClientModel clientModel;

}
