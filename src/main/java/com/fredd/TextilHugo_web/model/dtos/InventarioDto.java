package com.fredd.TextilHugo_web.model.dtos;

import com.fredd.TextilHugo_web.model.entities.TalleIndumentaria;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
    private IndumentariaDto indumentaria;
}
