package com.fredd.TextilHugo_web.model.dtos.request;

import com.fredd.TextilHugo_web.model.entities.Producto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInventarioDto {

    @NotNull(message = "La cantidad no debe ser nula")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private Integer cantidad;

    @NotNull(message = "El precio unitario no debe ser nulo")
    @Min(value = 1, message = "El precio unitario debe ser mayor a 0")
    private Double precioUnitario;

    @NotNull
    private Producto producto;
}
