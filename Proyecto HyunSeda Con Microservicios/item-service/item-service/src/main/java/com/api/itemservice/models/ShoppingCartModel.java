package com.api.itemservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ShoppingCart")
public class ShoppingCartModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany
    private List<ItemModel> items;
    @Column
    private double subtotal;

    public ShoppingCartModel() {}
    public ShoppingCartModel(int id, List<ItemModel> items) {
        this.id = id;
        this.items = items;
    }
}
