package com.fredd.TextilHugo_web.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventarioDto {
    private Long id;
    private Integer cantidad;
    private Double precioUnitario;
    private Date fechaDeLaCarga;
    private ProductoDto producto;
}
