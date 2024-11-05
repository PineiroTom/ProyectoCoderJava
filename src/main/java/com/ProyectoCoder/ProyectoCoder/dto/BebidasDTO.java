package com.ProyectoCoder.ProyectoCoder.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BebidasDTO {
    @Schema(description = "Identificador unico de la bebida", example = "1")
    private Long id;
    @Schema(description = "Nombre de la bebida", example = "Coca-Cola")
    private String name;
    @Schema(description = "Litros de la bebida", example = "2.25L")
    private double liters;
    @Schema(description = "Precio de la bebida", example = "$4000")
    private double price;

    public BebidasDTO() {
    }

    public BebidasDTO(Long id, String nombre, double litros, double precio) {
        this.id = id;
        this.name = nombre;
        this.liters = litros;
        this.price = precio;
    }

}
