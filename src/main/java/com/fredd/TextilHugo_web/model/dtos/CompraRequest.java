package com.fredd.TextilHugo_web.model.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CompraRequest {
    @NotNull
    private Long inventoryId;

    @NotNull
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private Integer quantity;

}
