package com.ProyectoCoder.ProyectoCoder.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ProyectoCoder.ProyectoCoder.dto.BebidasDTO;
import com.ProyectoCoder.ProyectoCoder.service.BebidasService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/bebidas")
public class BebidasController {

    @Autowired
    private final BebidasService bebidasService;

    public BebidasController(BebidasService bebidasService) {
        this.bebidasService = bebidasService;
    }

    @PostMapping
    public ResponseEntity<BebidasDTO> createBebida(@RequestBody BebidasDTO bebidasDTO) {
        BebidasDTO createdDomicilio = bebidasService.createBebidas(bebidasDTO);
        return ResponseEntity.ok(createdDomicilio);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BebidasDTO> getBebidasById(@PathVariable Long id) {
        BebidasDTO bebidas = bebidasService.getBebidasByID(id);
        return bebidas != null ? ResponseEntity.ok(bebidas) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<BebidasDTO>> getAllBebidas() {
        List<BebidasDTO> bebidas = bebidasService.getAllBebidas();
        return ResponseEntity.ok(bebidas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BebidasDTO> updateBebidas(@PathVariable Long id, @RequestBody BebidasDTO bebidasDTO) {
        BebidasDTO updatedBebidas = bebidasService.updateBebidas(id, bebidasDTO);
        return updatedBebidas != null ? ResponseEntity.ok(updatedBebidas) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBebidas(@PathVariable Long id) {
        bebidasService.deleteBebidas(id);
        return ResponseEntity.noContent().build();
    }
}