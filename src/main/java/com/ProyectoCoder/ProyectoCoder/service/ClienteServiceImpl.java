package com.ProyectoCoder.ProyectoCoder.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ProyectoCoder.ProyectoCoder.dto.BebidasDTO;
import com.ProyectoCoder.ProyectoCoder.dto.ClienteDto;
import com.ProyectoCoder.ProyectoCoder.mapper.BebidasMapper;
import com.ProyectoCoder.ProyectoCoder.mapper.ClienteMapper;
import com.ProyectoCoder.ProyectoCoder.mapper.ClienteMapperBuilder;
import com.ProyectoCoder.ProyectoCoder.model.Bebidas;
import com.ProyectoCoder.ProyectoCoder.model.Cliente;
import com.ProyectoCoder.ProyectoCoder.repository.BebidasRepository;
import com.ProyectoCoder.ProyectoCoder.repository.ClienteRepository;

import jakarta.transaction.Transactional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/users";

    @Autowired
    private final ClienteRepository clienteRepository;
    @Autowired
    private final ClienteMapper clienteMapper;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ClienteMapperBuilder clienteMapperBuilder;
    @Autowired
    private BebidasMapper bebidasMapper;
    @Autowired
    private BebidasRepository bebidasRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository, ClienteMapper clienteMapper,
            RestTemplate restTemplate, ClienteMapperBuilder clienteMapperBuilder, BebidasMapper bebidasMapper,
            BebidasRepository bebidasRepository) {

        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
        this.restTemplate = restTemplate;
        this.clienteMapperBuilder = clienteMapperBuilder;
        this.bebidasMapper = bebidasMapper;
        this.bebidasRepository = bebidasRepository;
    }

    public Cliente[] findAll() {
        return restTemplate.getForObject(BASE_URL, Cliente[].class);
    }

    @Override
    public ClienteDto getClientById(Long id) {
        return clienteRepository.findById(id)
                .map(clienteMapper::mapClienteToDto)
                .orElseThrow(() -> new RuntimeException("Cliente not found"));
    }

    public ClienteDto findById(Long id) {
        Optional<Cliente> existingUserOptional = clienteRepository.findById(id);

        if (existingUserOptional.isPresent()) {
            throw new RuntimeException("El usuario ya existe en la base de datos");
        }

        ClienteDto userDTO = restTemplate.getForObject(BASE_URL + "/{id}",
                ClienteDto.class, id);

        Cliente userWithSameName = clienteRepository.findByName(userDTO.getName());
        if (userWithSameName != null) {
            throw new RuntimeException("Ya existe un usuario con el mismo nombre en labase de datos");
        }

        Cliente newUser = clienteMapperBuilder.toEntity(userDTO);
        Cliente savedUser = clienteRepository.save(newUser);
        return clienteMapperBuilder.toDTO(savedUser);
    }

    @Override
    public ClienteDto createClient(ClienteDto clienteDto) {
        Cliente cliente = clienteMapperBuilder.toEntity(clienteDto);
        Cliente savedCliente = clienteRepository.save(cliente);
        return clienteMapper.mapClienteToDto(savedCliente);
    }

    @Override
    public List<ClienteDto> getAllClients() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllClients'");
    }

    @Override
    public void updateClient(Long id, ClienteDto clienteDto) {
        Cliente cliente = clienteMapperBuilder.toEntity(clienteDto);
        cliente.setId(id);
        clienteRepository.save(cliente);
    }

    @Override
    public void deleteClient(Long id) {
        restTemplate.delete(BASE_URL + "/{id}", id);
    }

    @Transactional
    public ClienteDto addBebidasToClient(Long clientId, BebidasDTO bebidasDTO) {
        Cliente cliente = clienteRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Bebidas bebida = bebidasMapper.toBebidas(bebidasDTO);
        bebida.setCliente(cliente);
        cliente.getBebidas().add(bebida);

        Cliente savedCliente = clienteRepository.save(cliente);
        return clienteMapper.mapClienteToDto(savedCliente);
    }

    public ClienteDto addBebidaToClient(Long clientId, Long bebidasId) {
        Cliente cliente = clienteRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Bebidas bebidas = bebidasRepository.findById(bebidasId)
                .orElseThrow(() -> new RuntimeException("Domicilio not found"));

        // Insertar en la tabla intermedia directamente
        clienteRepository.insertUserDomicilio(cliente.getId(), bebidas.getId());

        // Devolver el DTO del usuario actualizado
        return this.clienteMapperBuilder.toDTO(cliente);
    }

    public String saveClient(String cliente) {
        final String url = "https://jsonplaceholder.typicode.com/users";

        return restTemplate.postForObject(url, cliente, String.class);
    }
}
