package com.fredd.TextilHugo_web.model.dtos.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateTalleIndumentariaDto {
    @NotEmpty(message = "El campo 'talle' es obligatorio")
    private String talle;

    @NotNull
    @Min(value = 1, message = "La medida debe ser mayor que 0")
    private Integer medidaLargo;

    @NotNull
    @Min(value = 1, message = "La medida debe ser mayor que 0")
    private Integer medidaAncho;
}
