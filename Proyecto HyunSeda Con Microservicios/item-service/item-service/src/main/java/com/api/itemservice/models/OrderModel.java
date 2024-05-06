package com.api.itemservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Orders")
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String date;

    @OneToMany
    private List<ItemModel> items;

    @OneToOne
    @JoinColumn(name = "id")
    private ClientModel client;

    public OrderModel() {
    }
    public OrderModel(Long id, String date, List<ItemModel> items, ClientModel client) {
        this.id = id;
        this.date = date;
        this.items = items;
    }
}
