package com.ProyectoCoder.ProyectoCoder.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "BEBIDAS")
public class Bebidas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOMBRE")
    private String name;

    @Column(name = "LITROS")
    private double liters;

    @Column(name = "PRECIO")
    private double price;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Bebidas() {
    }

    public Bebidas(Long id, String name, double liters, double price, Cliente cliente) {
        this.id = id;
        this.name = name;
        this.liters = liters;
        this.price = price;
        this.cliente = cliente;
    }

}
