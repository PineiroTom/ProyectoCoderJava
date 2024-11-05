package com.ProyectoCoder.ProyectoCoder.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
@Table(name = "CLIENTE")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOMBRE")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE")
    private String phone;

    @OneToMany(mappedBy = "cliente")
    private List<Bebidas> bebidas = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(Long id, String name, String email, String phone, List<Bebidas> bebidas) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.bebidas = bebidas;
    }

    public void addBebidas(Bebidas bebida) {
        bebidas.add(bebida);
        ((Bebidas) bebidas).setCliente(this);
    }

    public void removeBebidas(Bebidas bebida) {
        bebidas.remove(bebida);
        ((Bebidas) bebidas).setCliente(null);
    }
}
