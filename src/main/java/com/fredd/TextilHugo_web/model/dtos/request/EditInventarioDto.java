package com.fredd.TextilHugo_web.model.dtos.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class EditInventarioDto {
    @NotNull(message = "La cantidad no debe ser nula")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private Integer cantidad;

    @NotNull(message = "El precio unitario no debe ser nulo")
    @Min(value = 1, message = "El precio unitario debe ser mayor a 0")
    private Double precioUnitario;

}
