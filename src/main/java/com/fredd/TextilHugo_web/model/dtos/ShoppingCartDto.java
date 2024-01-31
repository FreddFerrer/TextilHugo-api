package com.fredd.TextilHugo_web.model.dtos;

import com.fredd.TextilHugo_web.model.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartDto {
    private Long id;
    private InventarioDto producto;
    private Usuario usuario;
}
