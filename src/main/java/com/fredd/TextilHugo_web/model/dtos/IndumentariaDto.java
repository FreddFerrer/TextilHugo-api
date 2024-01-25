package com.fredd.TextilHugo_web.model.dtos;

import com.fredd.TextilHugo_web.model.entities.TalleIndumentaria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndumentariaDto {
    private Long id;
    private String tipo;
    private String descripcion;
    private String marca;
    private String genero;
    private String color;
    private String material;
    private String categoria;
    private String temporada;
    private TalleIndumentaria talleIndumentaria;
}
