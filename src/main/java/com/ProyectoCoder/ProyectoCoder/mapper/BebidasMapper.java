package com.ProyectoCoder.ProyectoCoder.mapper;

import org.springframework.stereotype.Component;

import com.ProyectoCoder.ProyectoCoder.model.Bebidas;
import com.ProyectoCoder.ProyectoCoder.dto.BebidasDTO;

@Component
public class BebidasMapper {
    public BebidasDTO toBebidasDTO(Bebidas bebidas) {
        if (bebidas == null) {
            return null;
        }

        return BebidasDTO.builder()
                .id(bebidas.getId())
                .name(bebidas.getName())
                .price(bebidas.getPrice())
                .liters(bebidas.getLiters())
                .build();

    }

    public Bebidas toBebidas(BebidasDTO bebidasDTO) {
        if (bebidasDTO == null) {
            return null;
        }

        Bebidas bebidas = new Bebidas();
        bebidas.setId(bebidasDTO.getId());
        bebidas.setName(bebidasDTO.getName());
        bebidas.setPrice(bebidasDTO.getPrice());
        bebidas.setLiters(bebidasDTO.getLiters());
        return bebidas;
    }
}
