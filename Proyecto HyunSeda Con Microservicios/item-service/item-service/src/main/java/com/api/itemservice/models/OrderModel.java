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
    @Column
    private String state;

    @OneToMany
    private List<ItemModel> items;

    @OneToOne
    @JoinColumn(name = "id")
    private ClientModel client;

    public OrderModel() {
    }
    public OrderModel(Long id, String date, String state, List<ItemModel> items, ClientModel client) {
        this.id = id;
        this.date = date;
        this.state = state;
        this.items = items;
    }
}
