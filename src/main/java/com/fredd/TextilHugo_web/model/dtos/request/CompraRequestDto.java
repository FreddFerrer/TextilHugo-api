package com.fredd.TextilHugo_web.model.dtos.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraRequestDto {
    @NotNull
    private Long inventoryId;

    @NotNull
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private Integer quantity;

}
