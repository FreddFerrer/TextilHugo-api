package com.fredd.TextilHugo_web.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartDto {
    private Long id;
    private InventarioDto producto;
    private UsuarioDto usuario;
    private Integer cantidad;
}
