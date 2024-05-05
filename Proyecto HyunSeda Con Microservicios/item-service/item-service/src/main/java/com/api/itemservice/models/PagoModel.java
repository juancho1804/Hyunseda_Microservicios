package com.api.itemservice.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Pago")
public class PagoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long idPago;

    @Column
    private String formaPago;

    @Column
    private double total;

}
