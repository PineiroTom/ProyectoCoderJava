package com.ProyectoCoder.ProyectoCoder.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProyectoCoder.ProyectoCoder.dto.BebidasDTO;
import com.ProyectoCoder.ProyectoCoder.mapper.BebidasMapper;
import com.ProyectoCoder.ProyectoCoder.model.Bebidas;
import com.ProyectoCoder.ProyectoCoder.repository.BebidasRepository;

@Service
public class BebidasService {
    @Autowired
    private final BebidasMapper bebidasMapper;

    @Autowired
    private final BebidasRepository bebidasRepository;

    public BebidasService(BebidasMapper bebidasMapper, BebidasRepository bebidasRepository) {
        this.bebidasMapper = bebidasMapper;
        this.bebidasRepository = bebidasRepository;
    }

    public BebidasDTO createBebidas(BebidasDTO bebidasDTO) {
        Bebidas bebidas = bebidasMapper.toBebidas(bebidasDTO);
        Bebidas savedBebida = bebidasRepository.save(bebidas);
        return bebidasMapper.toBebidasDTO(savedBebida);
    }

    public BebidasDTO getBebidasByID(Long id) {
        return bebidasRepository.findById(id)
                .map(bebidasMapper::toBebidasDTO)
                .orElse(null);
    }

    public List<BebidasDTO> getAllBebidas() {
        return bebidasRepository.findAll().stream()
                .map(bebidasMapper::toBebidasDTO)
                .collect(Collectors.toList());
    }

    public BebidasDTO updateBebidas(Long id, BebidasDTO bebidasDTO) {
        return bebidasRepository.findById(id)
                .map(bebidas -> {
                    bebidas.setName(bebidasDTO.getName());
                    bebidas.setPrice(bebidasDTO.getPrice());
                    bebidas.setLiters(bebidasDTO.getPrice());
                    return bebidasMapper.toBebidasDTO(bebidasRepository.save(bebidas));
                })
                .orElse(null);
    }

    public void deleteBebidas(Long id) {
        bebidasRepository.deleteById(id);
    }
}
