package com.ProyectoCoder.ProyectoCoder.mapper;

import org.springframework.stereotype.Component;

import com.ProyectoCoder.ProyectoCoder.dto.ClienteDto;
import com.ProyectoCoder.ProyectoCoder.model.Cliente;

@Component
public class ClienteMapper {

    public ClienteDto mapClienteToDto(Cliente Cliente) {
        if (Cliente == null) {
            throw new IllegalArgumentException("El usuario Entity no puede ser nulo");
        }

        ClienteDto ClienteDto = new ClienteDto();
        ClienteDto.setId(Cliente.getId());
        ClienteDto.setName(Cliente.getName());
        ClienteDto.setPhone(Cliente.getPhone());
        ClienteDto.setEmail(Cliente.getEmail());

        return ClienteDto;
    }

    public Cliente mapDTOtoCliente(ClienteDto clienteDto) {
        if (clienteDto == null) {
            throw new IllegalArgumentException("El usuarioDTO no puede ser nulo");
        }
        Cliente cliente = new Cliente();
        cliente.setId(clienteDto.getId());
        cliente.setName(clienteDto.getName());
        cliente.setPhone(clienteDto.getPhone());
        cliente.setEmail(clienteDto.getEmail());

        return cliente;
    }

}
