package com.fredd.TextilHugo_web.model.entities;

import jakarta.persistence.*;
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
public class Talle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_talle", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "talle", nullable = false)
    private String sizeChar;

    @NotNull
    @Column(name = "medida_largo", nullable = false)
    private Integer medidaLargo;

    @NotNull
    @Column(name = "medida_ancho, nullable = false")
    private Integer medidaAncho;

}
