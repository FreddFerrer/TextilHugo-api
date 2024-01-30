package com.fredd.TextilHugo_web.model.dtos.request;

import com.fredd.TextilHugo_web.model.entities.Talle;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductoDto {

    @NotEmpty(message = "El tipo no debe estar vacio ni ser menor a 10 caracteres")
    @Size(min = 1, max = 100, message = "La longitud del campo debe estar entre 10 y 100 caracteres")
    private String tipo;

    @NotEmpty(message = "La descripcion no debe estar vacia")
    @Size(min = 1, max = 100, message = "La longitud del campo debe estar entre 10 y 100 caracteres")
    private String descripcion;

    @NotEmpty(message = "Campo 'marca' requerido")
    private String marca;

    @NotEmpty(message = "Campo 'genero' requerido")
    private String genero;

    @NotEmpty(message = "Campo 'color' requerido")
    private String color;

    @NotEmpty(message = "Campo 'material' requerido")
    private String material;

    @NotEmpty(message = "Campo 'categoria' requerido")
    private String categoria;

    private String temporada;

    private Talle talle;
}
