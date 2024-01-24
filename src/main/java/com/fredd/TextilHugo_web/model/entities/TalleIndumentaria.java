package com.fredd.TextilHugo_web.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "talles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TalleIndumentaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotEmpty(message = "El campo 'talle' es obligatorio")
    private String sizeChar;

    @NotNull
    @Min(value = 1, message = "La medida debe ser mayor que 0")
    private Integer medidaLargo;

    @NotNull
    @Min(value = 1, message = "La medida debe ser mayor que 0")
    private Integer medidaAncho;

}
