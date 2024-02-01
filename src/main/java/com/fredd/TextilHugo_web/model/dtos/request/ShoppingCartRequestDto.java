package com.fredd.TextilHugo_web.model.dtos.request;

import com.fredd.TextilHugo_web.model.entities.Inventario;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ShoppingCartRequestDto {
    @NotNull
    private Inventario inventario;

    @NotNull
    private Integer cantidad;

}
