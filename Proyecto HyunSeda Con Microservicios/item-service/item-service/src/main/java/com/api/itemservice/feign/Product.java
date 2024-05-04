package com.api.itemservice.feign;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Product {
    @Id
    private Long id;
    @Column
    private String name;
    @Column
    private Double price;
    @Column
    private String description;
}
