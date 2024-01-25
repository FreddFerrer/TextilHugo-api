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
    @Column(name = "id_talle", nullable = false)
    private Long id;

    @Column(name = "talle")
    private String sizeChar;

    @Column(name = "medida_largo")
    private Integer medidaLargo;

    @Column(name = "medida_ancho")
    private Integer medidaAncho;

}
