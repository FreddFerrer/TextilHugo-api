package com.fredd.TextilHugo_web.model.dtos.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

//Cuerpo de la peticion de compra

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraRequestDto {
    @NotNull
    private Long inventarioId;

    @NotNull
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private Integer cantidad;

}
