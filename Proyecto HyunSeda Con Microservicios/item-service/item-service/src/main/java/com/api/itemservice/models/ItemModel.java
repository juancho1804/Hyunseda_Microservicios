package com.api.itemservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Items")
public class ItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @OneToOne
    @JoinColumn(name="id")
    private ProductModel product;

    @Getter
    @Column
    private Integer cantidad;

    @Column
    private double subtotal;



    public ItemModel() {
    }
    public ItemModel(ProductModel product) {
        this.product = product;
        this.cantidad = 0;
        this.subtotal = 0;
    }

    public ItemModel(ProductModel product, Integer cantidad) {
        this.product = product;
        this.cantidad = cantidad;
        this.subtotal = this.cantidad*this.product.getPrice();
    }


}
