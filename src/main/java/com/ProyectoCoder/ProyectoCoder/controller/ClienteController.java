package com.ProyectoCoder.ProyectoCoder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProyectoCoder.ProyectoCoder.dto.ClienteDto;
import com.ProyectoCoder.ProyectoCoder.model.Cliente;
import com.ProyectoCoder.ProyectoCoder.service.ClienteServiceImpl;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ProyectoCoder.ProyectoCoder.utils.ApiResponseMsg;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteServiceImpl ClienteService;

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuario por ID", description = "Retorna el usuario por su ID")
    @ApiResponse(responseCode = "200", description = "Successful!", content = @Content(schema = @Schema(implementation = ClienteDto.class)))
    @ApiResponse(responseCode = "404", description = "User not found!", content = @Content(schema = @Schema(implementation = ClienteDto.class)))
    public ResponseEntity<?> encontrarUserPorId(@PathVariable Long id) {
        try {
            ClienteDto user = ClienteService.findById(id);
            return ResponseEntity.ok(user);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("User not found", e.getMessage()));
        }
    }

    @GetMapping(path = "/all")
    @Operation(summary = "Obtener todos los clientes", description = "Retorna todos los clientes")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = ClienteDto.class))),
            @ApiResponse(responseCode = "404", description = "clients not found", content = @Content(schema = @Schema(implementation = ApiResponseMsg.class), examples = {
                    @ExampleObject(name = "clientNotFoundExample", value = "{\"message\": \"client not found\"}", description = "clientes no encontrados")
            }))
    })

    public ResponseEntity<?> getAllClients() {
        try {
            Cliente[] clientes = ClienteService.findAll();
            return ResponseEntity.ok().body(new ApiResponseMsg("Clientes encontrados ", clientes));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("Clientes no encontrados ", e.getMessage()));
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Void> updateClient(@PathVariable Long id, @RequestBody ClienteDto clienteDto) {
        ClienteService.updateClient(id, clienteDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Borrar un cliente de la DB", description = "Borra un cliente por su ID")
    @ApiResponse(responseCode = "204", description = "Client deleted")
    @ApiResponse(responseCode = "404", description = "Client not found")
    public ResponseEntity<?> deleteClient(
            @Parameter(description = "ID of the client to be deleted") @PathVariable Long id) {
        try {
            ClienteService.deleteClient(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("Client Deleted", e.getMessage()));
        }
    }

    @PostMapping("path")
    public ResponseEntity<ClienteDto> createCliente(@RequestBody ClienteDto clienteDto) {
        ClienteDto createdClient = ClienteService.createClient(clienteDto);
        return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
    }

}
