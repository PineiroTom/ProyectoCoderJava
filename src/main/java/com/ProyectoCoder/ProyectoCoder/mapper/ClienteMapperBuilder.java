package com.ProyectoCoder.ProyectoCoder.mapper;

import org.springframework.stereotype.Component;
import com.ProyectoCoder.ProyectoCoder.dto.BebidasDTO;
import com.ProyectoCoder.ProyectoCoder.dto.ClienteDto;
import com.ProyectoCoder.ProyectoCoder.model.Bebidas;
import com.ProyectoCoder.ProyectoCoder.model.Cliente;

@Component
public class ClienteMapperBuilder {
    public ClienteDto toDTO(Cliente cliente) {
        if (cliente == null) {
            return null;
        }
        return ClienteDto.builder()
                .id(cliente.getId())
                .name(cliente.getName())
                .email(cliente.getEmail())
                .phone(cliente.getPhone())
                .build();
    }

    public Cliente toEntity(ClienteDto dto) {
        if (dto == null) {
            return null;
        }
        return Cliente.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .build();
    }

    public static BebidasDTO toBebidasDTO(Bebidas bebidas) {
        BebidasDTO dto = new BebidasDTO();
        dto.setId(bebidas.getId());
        dto.setName(bebidas.getName());
        dto.setPrice(bebidas.getPrice());
        dto.setLiters(bebidas.getLiters());
        return dto;
    }
}
