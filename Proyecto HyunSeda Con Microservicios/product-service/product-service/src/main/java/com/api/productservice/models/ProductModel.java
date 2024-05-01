package com.api.productservice.models;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;


@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name="Product")
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @Column
    private String description;

    @Column
    private double price;

    @ManyToOne
    @JoinColumn(name="category_id")
    private CategoryModel category;

    public ProductModel(Long id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }


}
