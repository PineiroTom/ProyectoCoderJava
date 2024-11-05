package com.ProyectoCoder.ProyectoCoder.service;

import java.util.List;

import com.ProyectoCoder.ProyectoCoder.dto.ClienteDto;
import com.ProyectoCoder.ProyectoCoder.model.Cliente;

public interface ClienteService {

    Cliente[] findAll();

    ClienteDto getClientById(Long id);

    ClienteDto createClient(ClienteDto clienteDto);

    List<ClienteDto> getAllClients();

    void updateClient(Long id, ClienteDto clienteDto);

    void deleteClient(Long id);
}
