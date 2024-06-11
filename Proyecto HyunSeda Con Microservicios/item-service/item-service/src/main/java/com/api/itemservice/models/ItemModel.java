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


    /**
     * @brief Constructor por defecto de la clase ItemModel.
     *
     * Este constructor se utiliza para crear una nueva instancia de la clase ItemModel sin inicializar sus campos.
     */
    public ItemModel() {
    }

    /**
     * @brief Constructor de la clase ItemModel.
     *
     * Este constructor se utiliza para crear una nueva instancia de la clase ItemModel inicializando sus campos.
     *
     * @param product El producto asociado al item.
     * @param cantidad La cantidad del producto en el item.
     */
    public ItemModel(ProductModel product, Integer cantidad) {
        this.product = product;
        this.cantidad = cantidad;
        this.subtotal = this.cantidad*this.product.getPrice();
    }


}
