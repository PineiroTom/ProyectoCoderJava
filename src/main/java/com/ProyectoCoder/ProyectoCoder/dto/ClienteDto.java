package com.ProyectoCoder.ProyectoCoder.dto;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.ProyectoCoder.ProyectoCoder.mapper.ClienteMapperBuilder;
import com.ProyectoCoder.ProyectoCoder.model.Cliente;
import com.ProyectoCoder.ProyectoCoder.repository.ClienteRepository;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteDto {
    @Schema(description = "Identificador unico del cliente", example = "1")
    private long id;
    @Schema(description = "Nombre del cliente", example = "John Doe")
    private String name;
    @Schema(description = "Direccion email del cliente", example = "john.doe@example.com")
    private String email;
    @Schema(description = "Numero telefonico del cliente", example = "1234567890")
    private String phone;

    @Schema(description = "Lista de bebidas asociadas con el cliente")
    private Set<Long> BebidasIds;

    public ClienteDto() {
    }

    public ClienteDto(long id, String name, String email, String phone, Set<Long> BebidasIds,
            ClienteRepository clienteRepository) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.BebidasIds = BebidasIds;
        this.clienteRepository = clienteRepository;
    }

    public Set<BebidasDTO> getBebidasByUserId(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        return cliente.getBebidas()
                .stream()
                .map(ClienteMapperBuilder::toBebidasDTO)
                .collect(Collectors.toSet());
    }

    @Autowired
    private ClienteRepository clienteRepository;

}