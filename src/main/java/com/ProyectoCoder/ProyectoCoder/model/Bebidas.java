package com.ProyectoCoder.ProyectoCoder.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "BEBIDAS")
public class Bebidas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "LITROS")
    private double litros;

    @Column(name = "PRECIO")
    private double precio;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;
}
