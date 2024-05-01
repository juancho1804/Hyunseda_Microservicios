package com.api.itemservice.models;
import com.api.itemservice.feign.Product;
import jakarta.persistence.*;
import lombok.Setter;

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
    private Product product;
    private Integer amount;

    public ItemModel() {
    }
    public ItemModel(Product product) {
        this.product = product;
        this.amount = 0;
    }

    public ItemModel(Product producto, Integer cantidad) {
        this.product = producto;
        this.amount = cantidad;
    }

    public Product getProducto() {
        return product;
    }

    public Integer getCantidad() {
        return amount;
    }

    public Double getTotal() {
        if (product != null) {
            return product.getPrice() * amount.doubleValue();
        } else {
            return 0.0; // Otra acción adecuada en caso de que el producto sea nulo
        }
    }

}
